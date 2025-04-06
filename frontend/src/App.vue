<script setup>
const tab = ref(0)

const tabItems = [
	{
		name: "Find Dogs",
		icon: "mdi-dog",
		color: "brown"
	},
	{
		name: "Favorites",
		icon: "mdi-star",
		color: "warning"
	}
]
</script>

<template>
	<v-app>
		<s-appbar
			v-model="tab"
			:items="tabItems"
		/>
		<v-main>
			<v-window v-model="tab" class="page-container">
				<v-window-item class="page-window">
					<dog-carousel />
				</v-window-item>
				<v-window-item class="page-window">
					<dog-favorites />
				</v-window-item>
			</v-window>
		</v-main>
	</v-app>
</template>

<style scoped lang="scss">
.page-container {
	min-height: calc(100vh - var(--v-layout-top));
	max-width: max(728px, 70vw);
	margin: auto;
	padding: 16px 24px;
}
</style>