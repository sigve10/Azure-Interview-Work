<script setup>
import { dogApi } from '@/plugins/dogFetcher';

const emit = defineEmits(["refresh"])

const breeds = ref(["None"])
const breed = ref("None")

dogApi.getBreeds()
	.then(data => breeds.value = ["None", ...data])

function onRefresh() {
	const breedToEmit = breed.value == "None" ? undefined : breed.value;
	emit('refresh', breedToEmit)
}
</script>

<template>
	<div class="dog-action-wrapper">
		<v-autocomplete
			v-model="breed"
			:items="breeds"
			:hide-details="true"
			variant="outlined"
			color="primary"
			label="Breed"
		/>
		<v-btn
			append-icon="mdi-refresh"
			color="primary"
			variant="outlined"
			height="100%"
			@click="onRefresh"
		>
			Get new dogs
		</v-btn>
	</div>
</template>

<style scoped lang="scss">
.dog-action-wrapper {
	width: 100%;

	display: grid;
	grid-template-columns: 2fr;
	grid-auto-columns: 1fr;
	grid-auto-rows: 1fr;

	gap: 16px;

	@media screen and (min-width: 600px) {
		gap: 24px;
		grid-auto-flow: column;
	}
}
</style>