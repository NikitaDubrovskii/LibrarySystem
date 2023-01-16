<template>
  <div>
    <h1 class="subheading grey--text">Books</h1>
    <books-form v-if="profile.role === 'ADMIN'" class="right"/>

    <v-container class="my-5">
      <books-row v-for="book in sortedBooks"
                 :key="book.id"
                 :book="book"
                 :editBook="editBook"
                 class="mb-1 elevation-1"/>
    </v-container>

  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import BooksRow from "components/books/BooksRow.vue"
import BooksForm from "components/books/BooksForm.vue"

export default {
  components: {
    BooksRow,
    BooksForm
  },

  data() {
    return {
      book: null
    }
  },

  computed: {
    ...mapGetters(['sortedBooks']),
    ...mapState(['profile'])
  },

  methods: {
    editBook(book) {
      this.book = book
    }
  }
}
</script>

<style scoped>

</style>