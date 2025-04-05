<script setup>
import { fetchDog } from '@/plugins/dogFetcher';

const DOG_COUNT = 5

const currentDogs = ref([])
const selectedWindow = ref(0)

function fetchNewDogs(count) {
	fetchDog(count)
		.then((urls) => currentDogs.value = urls)
		.then(() => selectedWindow.value = 0)
}

fetchNewDogs(DOG_COUNT)
</script>

<template>
	<div class="dog-carousel">
		<v-carousel v-model="selectedWindow" height="auto">
			<v-carousel-item v-for="dog in currentDogs" :key="dog">
				<dog-image :src="dog" />
			</v-carousel-item>
		</v-carousel>
		<v-btn
			color="primary"
			block
			@click="fetchNewDogs(DOG_COUNT)"
		>
			Find new dogs!
		</v-btn>
	</div>
</template>

<style scoped lang="scss">
.dog-carousel {
	display: flex;
	align-items: center;
	flex-direction: column;
	gap: 16px;

	width: 100%;

}
</style>