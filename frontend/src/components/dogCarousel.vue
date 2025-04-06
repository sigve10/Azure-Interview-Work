<script setup>
import { dogApi } from '@/plugins/dogFetcher'

const DOG_COUNT = 12

const currentDogs = ref([])
const selectedWindow = ref(0)

function fetchNewDogs(count, breed=undefined) {
	dogApi.getRandomDogs(count, breed)
		.then((urls) => currentDogs.value = urls)
		.then(() => selectedWindow.value = 0)
}

function onRefresh(breed) {
	fetchNewDogs(DOG_COUNT, breed)
}

fetchNewDogs(DOG_COUNT)
</script>

<template>
	<div>
		<div class="more-dog-btn">
			<s-dog-actions @refresh="onRefresh"/>
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