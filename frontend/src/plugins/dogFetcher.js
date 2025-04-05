const DOG_FALLBACK = "https://images.dog.ceo/breeds/spitz-japanese/beet-005.jpg"
const DOG_URL = "https://dog.ceo/api/breeds/image/random/"

export const fetchDog = async (count) => {
	return fetch(DOG_URL + count)
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
}