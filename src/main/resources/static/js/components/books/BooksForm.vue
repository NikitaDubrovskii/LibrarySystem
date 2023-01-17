<template>
  <v-dialog v-model="dialog" max-width="600px">
    <v-btn slot="activator" @click="dialog = true">Add new book</v-btn>
    <v-card class="text-xs-center">
      <v-card-title>
        <h2>Add new book</h2>
      </v-card-title>
      <v-card-text>
        <v-text-field label="Title" outline placeholder="Write title" v-model="title" />
        <v-text-field label="Author" outline placeholder="Write author" v-model="author" />
        <v-text-field label="Year" outline placeholder="Write year" v-model="year" />
        <v-text-field label="Pages" outline placeholder="Write pages" v-model="pages" />
        <v-btn @click="save">Save</v-btn>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  props: ['bookAttr'],

  data() {
    return {
      dialog: false,
      title: '',
      author: '',
      year: '',
      pages: '',
      id: ''
    }
  },

  watch: {
    bookAttr(newVal) {
      this.title = newVal.title
      this.author = newVal.author
      this.year = newVal.year
      this.pages = newVal.pages
      this.id = newVal.id
    }
  },

  methods: {
    ...mapActions(['addBookAction']),

    save() {
      const book = {id: this.id, title: this.title, author: this.author, year: this.year, pages: this.pages}

      this.addBookAction(book)

      this.dialog = false

      this.title = ''
      this.author = ''
      this.year = ''
      this.pages = ''
      this.id = ''
    }
  }
}
</script>

<style scoped>

</style>