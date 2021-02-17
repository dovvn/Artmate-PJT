<template>
<div class="link-share">
    <b-modal :id="'pos-share-modal'+nf.id" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
        <div class="pos-check-modal-body">
        <div class="pos-check-title">SNS 공유하기</div>
        <div class="pos-check-body">
            <div class="link-share-btn">
            <a id="naver-link-btn" @click="naverShare">
                <img src="../../assets/naver_square_30x30.png" />
            </a>
            </div>
            <div class="link-share-btn">
            <a id="kakao-link-btn" @click="kakaoShare">
                <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" height="30px" width="30px"/>
            </a>
            </div>
            <div class="link-share-text">
            <a type="button" @click="urlCopy">
                <font-awesome-icon :icon="['far', 'copy']"/>
            <input id="urlText" v-model="share.url" style="margin:5px">
            </a>
            </div>
            <div><p v-if="copy">복사되었습니다.</p></div>
        </div>
        <div class="pos-check-bottom">
            <button class="pos-check-no-button"  @click="$bvModal.hide('pos-share-modal'+nf.id);copy=false;" style="margin-left: 0;">
            취소
            </button>
        </div>
        </div>
    </b-modal>
    <font-awesome-icon class="feed__interactions__share" icon="share-alt" @click="setShare();$bvModal.show('pos-share-modal'+nf.id);"/>
    </div>
</template>
<script>
export default {
    props : [
        'nf',
    ],
    data() {
        return {
            share : {
                url : '',
                title : ''
            },
            copy : false,
        }
    },
    methods: {
        urlCopy(){
            document.getElementById("urlText").select();
            document.execCommand('copy');
            this.copy = true;
        },
        naverShare() {
            var url = encodeURI(encodeURIComponent(this.share.url));
            var title = encodeURI(this.share.title);
            var shareURL =
                "https://share.naver.com/web/shareView.nhn?url=" +
                url +
                "&title=" +
                title;
            window.open(
                shareURL,
                "naversharedialog",
                "menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=500"
            );
        },
        kakaoShare() {
            window.Kakao.Link.sendDefault({
                objectType: "feed",
                content: {
                title: this.nf.userName,
                description: this.nf.feedText,
                imageUrl: this.nf.feedImg,
                link: {
                    mobileWebUrl: this.share.url,
                    webUrl: this.share.url,
                },
                },
                social: {
                likeCount: this.nf.likeCnt,
                commentCount: this.nf.commentCnt,
                sharedCount: 0,
                },
                buttons: [
                {
                    title: "웹으로 보기",
                    link: {
                    mobileWebUrl: this.share.url,
                    webUrl: this.share.url,
                    },
                },
                ],
            });
        },
        setShare(){
            this.share.url = "https://i4b202.p.ssafy.io/userfeed/detail/"+this.nf.id;
            this.share.title = this.nf.userName;
        }
    },
}
</script>

<style scoped>
.link-share{
    display: inline;
    margin-left: 5px;
}
.pos-check-yes-button {
  color:white;
  background-color:#CB3E47;
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
}
.pos-check-no-button {
  color:#F3F3F3;
  background-color:#707070;
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
  margin-left:15px;
}
::v-deep .pos-check-modal > .modal-dialog >.modal-content{
  background-color: #E8E8E8;
  border: 1px solid #707070;
  border-radius:15px;
  font-size:14px;
  width:310px;
  margin:auto;
}
.pos-check-title {
  height:60px;
  line-height:60px;
  font-weight:700;
}
.pos-check-modal-body {
  text-align:center;
  
}
.link-share-btn{
  display: inline-block;
  margin : 5px;
}
.link-share-text{
  margin: 5px;
}
</style>