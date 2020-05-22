<template>
    <div class="container">
        <h1>{{cmsHeader}}</h1>
        <p v-bind:style="{ textAlign: 'justify'}">{{cmdBody}}</p>
    </div>
</template>
<script type="text/javascript">
    export default{
        name:'Cms',
        data(){

            var slug = this.$route.params.slug;

            var cmsHeader = '';
            var desc = '';
            if(slug == 'about'){
                cmsHeader = this.content.about.title;
                desc = this.content.about.desc;
            }else if(slug == 'delivary-info'){
                cmsHeader = 'Delivery Infomation';
            }else if(slug == 'privacy-policy'){
                cmsHeader = 'Privacy and Policy';
            }

            return {
                cmsHeader:cmsHeader,
                cmdBody:desc,
            }
        },
        watch:{
            $route (to){
                var slug = to.params.slug;                
                if(slug == 'about'){
                    this.cmsHeader = this.content.about.title;
                }else if(slug == 'delivary-info'){
                    this.cmsHeader = 'Delivery Infomation';
                }else if(slug == 'privacy-policy'){
                    this.cmsHeader = 'Privacy and Policy';
                }else{
                    this.cmsHeader = '';
                }
            }
        }
    }
</script>