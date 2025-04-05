<script setup>
const tab = ref(0)
</script>

<template>
	<v-app>
		<v-app-bar>
			<v-tabs>
				<v-tab>
					Dogs
				</v-tab>
				<v-tab>
					Favorites
				</v-tab>
			</v-tabs>
		</v-app-bar>
		<v-main>
			<v-window>
				<v-window-item class="page-window">

				</v-window-item>
				<v-window-item class="page-window">

				</v-window-item>
			</v-window>
		</v-main>
	</v-app>
</template>

<style scoped lang="scss">
.page-window {
	min-height: calc(100vh - var(--v-layout-top));
	max-width: 728px;
	margin: auto;
	padding: 16px 24px;
}
</style>