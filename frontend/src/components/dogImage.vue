<script setup>
import { useUserStore } from '@/stores/userStore'

const userStore = useUserStore()

const props = defineProps({
	src: {
		type: String,
		required: true
	}
})
const popupActive = ref(false)

const isFavorite = computed({
	get() {
		return userStore.favorites.includes(props.src)
	},
	set(value) {
		userStore.setFavorite(props.src, value)
	}
})

function onFavoriteClicked() {
	const newValue = !isFavorite.value
	if (userStore.email === undefined) {
		popupActive.value = true;
	} else {
		isFavorite.value = newValue
	}
}
</script>

<template>
	<v-sheet
		class="dog-sheet"
		:style="{ '--dog-src': `url(${props.src})` }"
		v-ripple
	>
		<div
			aria-hidden
			class="background-image"
		/>
		<v-img
			:src="props.src"
			aspect-ratio="1"
			alt="A dog"
			class="dog-image"
			eager
		>
			<v-dialog activator="parent" class="dog-preview">
				<v-img
					:src="props.src"
					alt="A dog"
					max-height="80vh"
					class="dog-preview-image"
				/>
			</v-dialog>
		</v-img>
		<v-btn
			class="favorite-button"
			:class="{'active': isFavorite}"
			icon
			color="amber"
			variant="text"
			density="comfortable"
			@click="onFavoriteClicked"
		>
			<v-icon color="amber">
				mdi-star{{ isFavorite ? '' : '-outline' }}
			</v-icon>
			<login-popup v-model="popupActive" />
		</v-btn>
	</v-sheet>
</template>

<style scoped lang="scss">
.dog-sheet {
	--dog-src: "";
	position: relative;
	cursor: pointer;


	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;

	.background-image {
		background-image: var(--dog-src);
		background-size: cover;
		background-position: center;

		position: absolute;
		width: 100%;
		height: 100%;
		filter: blur(8px);
		pointer-events: none;

		opacity: 0.6;
	}

	&:hover .favorite-button {
		opacity: 1 !important;
	}

	.favorite-button {
		position: absolute;
		top: 8px;
		right: 8px;

		&:not(.active) {
			opacity: 0.2;
		}
	}

	.dog-image, .favorite-button {
		z-index: 1;
	}

	.dog-image {
		object-fit: contain;
		width: 100%;
		height: 100%;
	}

}
.dog-preview {
	max-height: 100vh;
	max-width: 80vw;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>