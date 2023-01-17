 <template>
  <v-dialog v-model="dialog" max-width="600px">
    <v-btn slot="activator" @click="dialog = true" small icon>
      <v-icon>edit</v-icon>
    </v-btn>
    <v-card class="text-xs-center">
      <v-card-title>
        <h2>Edit person</h2>
      </v-card-title>
      <v-card-text>
        <v-text-field label="First Name" outline placeholder="Write first name" v-model="firstName" />
        <v-text-field label="Second Name" outline placeholder="Write second name" v-model="secondName" />
        <v-text-field label="Age" outline placeholder="Write age" v-model="age" />
        <v-text-field label="Email" outline placeholder="Write email" v-model="email" />
        <v-text-field label="Phone" outline placeholder="Write phone" v-model="phone" />
        <v-text-field label="Locale" outline placeholder="Write locale" v-model="locale" />
        <v-btn @click="save">Save</v-btn>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
  import { mapActions } from "vuex";

  export default {
    props: ['user', 'userAttr'],

    data() {
      return {
        dialog: false,
        firstName: this.user.firstName,
        secondName: this.user.secondName,
        age: this.user.age,
        email: this.user.email,
        phone: this.user.phone,
        locale: this.user.locale,
        id: this.user.id
      }
    },

    watch: {
      userAttr(newVal) {
        this.firstName = newVal.firstName
        this.secondName = newVal.secondName
        this.age = newVal.age
        this.email = newVal.email
        this.phone = newVal.phone
        this.locale = newVal.locale
        this.id = newVal.id
      }
    },

    methods: {
      ...mapActions(['updateUserAction']),

      save() {
        const user = {id: this.id, firstName: this.firstName, secondName: this.secondName, age: this.age, email: this.email, phone: this.phone, locale: this.locale}

        this.updateUserAction(user)

        this.dialog = false

        this.firstName = ''
        this.secondName = ''
        this.age = ''
        this.email = ''
        this.phone = ''
        this.locale = ''
        this.id = ''
      }
    }
  }
</script>

<style scoped>

</style>