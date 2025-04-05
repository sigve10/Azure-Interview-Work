<script setup>
const props = defineProps({
	dogs: {
		type: Array,
		default: []
	}
})
</script>

<template>
	<div class="dog-grid">
		<dog-image v-for="dog in props.dogs" :key="dog" :src="dog" />
	</div>
</template>

<style scoped lang="scss">
.dog-grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(256px, 1fr));
}
</style>