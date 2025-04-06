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
	gap: 8px;
	padding: 8px;
	background-color: rgba(var(--v-theme-on-surface), 0.1)
}
</style>