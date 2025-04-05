<script setup>
import { useUserStore } from '@/stores/userStore';

const emit = defineEmits(["success", "closed"])

const model = defineModel({
	modelValue: Boolean
})

const userStore = useUserStore()
const mail = ref("")
const error = ref(undefined)

function onOk() {
	if (userStore.setMail(mail.value)) {
		model.value = false
		emit("success")
	} else {
		error.value = ["Invalid email"]
	}
}
</script>

<template>
	<v-dialog v-model="model" max-width="328px">
		<v-card>
			<v-card-title>
				Please enter your email
			</v-card-title>
			<v-card-text>
				<slot>
					Please enter your email in order to save favorites.
				</slot>

				<v-text-field
					v-model="mail"
					label="user@domain.com"
					variant="outlined"
					class="mt-3"
					:error-messages="error"
					hide-details="auto"
				/>
			</v-card-text>
			<v-card-actions>
				<v-btn variant="outlined" @click="model = false">
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