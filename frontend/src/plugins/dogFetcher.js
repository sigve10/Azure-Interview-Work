const DOG_FALLBACK = "https://images.dog.ceo/breeds/spitz-japanese/beet-005.jpg"
const BASE_URL = "https://dog.ceo/api"
const RANDOM_DOGS_URL = BASE_URL + "/breeds/image/random/"
const RANDOM_DOGS_BY_BREED = BREED => `${BASE_URL}/breed/${BREED}/images/random/`
const BREEDS_LIST_URL = BASE_URL + "/breeds/list/all"

export const dogApi = {
	async getRandomDogs(count, breed=undefined) {
		let url = RANDOM_DOGS_URL

		// Loose check to also match empty string
		if (breed != undefined) {
			url = RANDOM_DOGS_BY_BREED(breed.toLowerCase());
		}

		return fetch(url + count)
			.then(response => {
				if (!response.ok) {
					throw new Error("Failed to fetch dog");
				}

				return response.json()
			}).then(data => {
				if (!("message" in data)) {
					return [DOG_FALLBACK]
				}

				if (data.message instanceof String) {
					return [data.message]
				}

			return data.message
		})
	},

	async getBreeds() {
		return fetch(BREEDS_LIST_URL)
			.then(response => {
				if (!response.ok) {
					throw new Error("Failed to fetch breeds")
				}

				return response.json()
			}).then(data => {
				if (!("message" in data)) {
					return []
				}

				// Ignore sub-breeds for now
				return Object.keys(data.message).map(capitalize)
			})

		function capitalize(str) {
			return str.charAt(0).toUpperCase() + str.slice(1)
		}
	}
}