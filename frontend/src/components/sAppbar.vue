<script setup>
const props = defineProps({
	items: {
		type: Array,
		default: []
	}
})

const model = defineModel({
	modelValue: Number
})
</script>

<template>
	<v-app-bar>
		<v-tabs
			v-model="model"
			grow
		>
			<v-tab
				v-for="item in items"
				:key="item.name"
				:color="item.color || undefined"
			>
				<v-icon
					v-if="item.icon"
					class="mr-3"
				>
					{{ item.icon }}
				</v-icon>
				<span v-if="item.name">
					{{ item.name }}
				</span>
			</v-tab>
		</v-tabs>
	</v-app-bar>
</template>

<style scoped lang="scss">
.s-tab-item {
	display: flex;
	gap: 24px;
}
</style>