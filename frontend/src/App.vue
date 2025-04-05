<script setup>
const tab = ref(0)
</script>

<template>
	<v-app>
		<v-app-bar>
			<v-tabs v-model="tab">
				<v-tab>
					Dogs
				</v-tab>
				<v-tab>
					Favorites
				</v-tab>
			</v-tabs>
		</v-app-bar>
		<v-main>
			<v-window v-model="tab" class="page-container">
				<v-window-item class="page-window">
					<dog-carousel />
				</v-window-item>
				<v-window-item class="page-window">

				</v-window-item>
			</v-window>
		</v-main>
	</v-app>
</template>

<style scoped lang="scss">
.page-container {
	min-height: calc(100vh - var(--v-layout-top));
	max-width: 728px;
	margin: auto;
	padding: 16px 24px;
}
</style>