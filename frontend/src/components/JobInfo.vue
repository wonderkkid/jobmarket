<template>

<v-data-table
    :headers="headers"
    :items="jobInfo"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'JobInfo',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
            { text: "jobId", value: "jobId" },
            { text: "jobTitle", value: "jobTitle" },
            { text: "jobPayment", value: "jobPayment" },
            { text: "jobStatus", value: "jobStatus" },
            { text: "freelancerName", value: "freelancerName" },
        ],
        jobInfo : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/jobinfos')

      this.jobInfo = temp.data._embedded.jobinfos;

    },
    methods: {
    }
  }
</script>

