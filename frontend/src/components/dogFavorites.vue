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
		<div v-if="userStore.email === undefined">
			<v-btn>
				Login to see your favorites
				<login-popup activator="parent" @success="attemptGetFavorites" >
					Please enter your email in order to see your favorites.
				</login-popup>
			</v-btn>
		</div>
		<dog-grid :dogs="userStore.favorites" />
	</div>
</template>