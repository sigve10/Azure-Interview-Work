<script setup>
import { fetchDog } from '@/plugins/dogFetcher';

const DOG_COUNT = 12

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
	<div>
		<div class="more-dog-btn">
			<v-btn color="primary" @click=fetchNewDogs(DOG_COUNT)>
				Find new dogs
			</v-btn>
		</div>
		<dog-grid :dogs="currentDogs" />
	</div>
</template>

<style scoped lang="scss">
.more-dog-btn {
	width: 100%;
	display: flex;
	justify-content: center;
	margin: 16px 8px;
}
</style>