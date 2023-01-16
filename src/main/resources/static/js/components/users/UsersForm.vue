<template>
  <v-dialog max-width="600px">
    <v-btn slot="activator">Add new person</v-btn>
    <v-card class="text-xs-center">
      <v-card-title>
        <h2>Add new person</h2>
      </v-card-title>
      <v-card-text>
        <v-text-field label="First Name" outline placeholder="Write first name" v-model="firstName" />
        <v-text-field label="Second Name" outline placeholder="Write second name" v-model="secondName" />
        <v-text-field label="Age" outline placeholder="Write age" v-model="age" />
        <v-btn @click="save">Save</v-btn>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  props: ['userAttr'] ,

  data() {
    return {
      firstName: '',
      secondName: '',
      age: '',
      id: ''
    }
  },

  watch: {
    userAttr(newVal) {
      this.firstName = newVal.firstName
      this.secondName = newVal.secondName
      this.age = newVal.age
      this.id = newVal.id
    }
  },

  methods: {
    ...mapActions(['addUserAction']),

    save() {
      const user = {id: this.id, firstName: this.firstName, secondName: this.secondName, age: this.age}

      this.addUserAction(user)


      this.firstName = ''
      this.secondName = ''
      this.age = ''
      this.id = ''

    }
  }
}

</script>

<style scoped>

</style>