<script setup>
import { useUserStore } from '@/stores/userStore'
import { onMounted } from 'vue'

const userStore = useUserStore()

const props = defineProps({
	src: {
		type: String,
		required: true
	}
})

const isFavorite = ref(false)
const popupActive = ref(false)

function onFavoriteClicked() {
	const newValue = !isFavorite.value
	if (userStore.email === undefined) {
		popupActive.value = true;
	} else {
		isFavorite.value = newValue
		userStore.setFavorite(props.src, newValue)
	}
}

onMounted(() => {
	userStore.getIsFavorite(props.src)
		.then(value => isFavorite.value = value)
})
</script>

<template>
	<v-sheet
		class="dog-sheet"
		:style="{ '--dog-src': `url(${props.src})` }"
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
		/>
		<v-btn
			class="favorite-button"
			icon
			color="amber"
			variant="text"
			size="x-large"
			@click="onFavoriteClicked"
		>
			<v-icon>
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


	display: flex;
	justify-content: center;
	align-items: center;

	.background-image {
		background-image: var(--dog-src);
		background-size: cover;
		background-position: center;

		position: absolute;
		width: 100%;
		height: 100%;
		filter: blur(42px);
		opacity: 0.7;
		pointer-events: none;
	}

	.favorite-button {
		position: absolute;
		top: 16px;
		right: 16px;
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
</style>