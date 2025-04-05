import { defineStore } from "pinia";
import { BACKEND_URL } from "@/plugins/config";

const MAIL_REGEX = /^\S+@\S+\.\S{2,3}$/

export const useUserStore = defineStore("user", {
	state: () => ({
		email: undefined,
		favorites: []
	}),
	actions: {
		setMail(mail) {
			if (MAIL_REGEX.test(mail)) {
				this.email = mail
				this.refreshFavorites()

				return true;
			}
			return false;
		},

		async refreshFavorites() {
			if (!this.email) {
				this.favorites = []
				return
			}

			return fetch(`${BACKEND_URL}/user/${this.email}`)
				.then(response => {
					if (!response.ok) {
						throw new Error();
					}

					return response.json()
				}).then(data => {
					this.favorites = data
					return data
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
				}).then(() => {
					if (value) {
						this.favorites.push(url)
					} else {
						const index = this.favorites.indexOf(url)

						if (index > -1) {
							this.favorites.splice(index, 1)
						}
					}
				})
		}
	}
})