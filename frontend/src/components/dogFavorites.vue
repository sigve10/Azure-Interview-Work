<script setup>
import { useUserStore } from '@/stores/userStore';
import { onMounted } from 'vue';

const userStore = useUserStore()

function attemptGetFavorites() {
	if (!userStore.email) {
		return;
	}
}

onMounted(attemptGetFavorites)
</script>

<template>
	<div>
		<div v-if="userStore.email === undefined" class="login-container">
			<v-btn color="primary">
				Login to see your favorites
				<login-popup activator="parent" @success="attemptGetFavorites" >
					Please enter your email in order to see your favorites.
				</login-popup>
			</v-btn>
		</div>
		<dog-grid v-else :dogs="userStore.favorites" />
	</div>
</template>

<style scoped lang="scss">
.login-container {
	height: 80vh;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>