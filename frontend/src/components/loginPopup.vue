<script setup>
import { useUserStore } from '@/stores/userStore';

const userStore = useUserStore()
const mail = ref("")
const error = ref(undefined)

const dialogState = ref(false)

function onOk() {
	if (userStore.setMail(mail.value)) {
		dialogState.value = false
	} else {
		error.value = ["Invalid email"]
	}
}
</script>

<template>
	<v-dialog v-model="dialogState" max-width="328px">
		<v-card>
			<v-card-title>
				Please enter your email
			</v-card-title>
			<v-card-text>
				<slot>
					Please enter your email in order to save favorites.
				</slot>

				<v-text-field v-model="mail" :error-messages="error"/>
			</v-card-text>
			<v-card-actions>
				<v-btn variant="outlined" @click="dialogState = false">
					Cancel
				</v-btn>
				<v-btn
					color="primary"
					variant="flat"
					@click="onOk"
				>
					Ok
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>