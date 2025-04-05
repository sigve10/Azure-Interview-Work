import { defineStore } from "pinia";
import { BACKEND_URL } from "@/plugins/config";

const MAIL_REGEX = /^\S+@\S+\.\S{2,3}$/

export const useUserStore = defineStore("user", {
	state: () => ({
		email: undefined
	}),
	actions: {
		setMail(mail) {
			if (MAIL_REGEX.test(mail)) {
				this.email = mail
				return true;
			}
			return false;
		},

		async getIsFavorite(url) {
			if (!this.email) {
				return new Promise(() => false)
			}

			return fetch(`${BACKEND_URL}/user/${this.email}`)
				.then((response) => {
					if (!response.ok) {
						throw new Error();
					}
					return response.json()
				}).then((data) => {
					return (data.includes(url))
				})
		},

		async setFavorite(url, value) {
			if (!this.email) {
				return new Promise(() => false)
			}

			const payload = {
				method: value ? "POST" : "DELETE",
				body: url,
			}

			return fetch(`${BACKEND_URL}/user/${this.email}`, payload)
				.then((response) => {
					if (!response.ok) {
						throw new Error();
					}

					return true;
				})
		}
	}
})