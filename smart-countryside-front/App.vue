<script>
export default {
  onLaunch: function () {
    // console.log("Main App Launch");
  },
  onReady: function () {
    // console.log("App onReady");
  },
  onShow: function () {
    // console.log("App onShow");
  },
  onHide: function () {
    // console.log("App Hide");
  },
  globalData: {
    showToast: function (msg) {
      uni.showToast({
        title: msg,
        icon: "none",
        duration: 1000,
      });
    },
    showLoading: function (msg) {
      if (!msg) {
        msg = "加载中...";
      }

      uni.showLoading({
        title: msg,
      });
    },
    showSuccessToast: function (msg) {
      uni.showToast({
        title: msg,
        icon: "success",
        duration: 1000,
      });
    },
    showFailToast: function (msg) {
      if (!msg) {
        msg = "加载失败！";
      }

      uni.showToast({
        title: msg,
        icon: "error",
        duration: 1000,
      });
    },
    uploadImages: function (images, listener) {
      let mThis = this;
      let image = null;
      console.log(images);

      for (var i = 0; i < images.length; i++) {
        if (images[i].fileid == null) {
          image = images[i];
          break;
        }
      }

      if (image != null) {
        this.upload(image.path, function (res, e) {
          console.log("uploadimage:", res);

          if (res && !e) {
            image.fileid = res.attributes.fileid;
            mThis.uploadImages(images, listener);
          } else if (!res && e) {
            listener(false);
          }
        });
      } else {
        listener(true);
      }
    },
	
    upload: function (path, response) {
      if (!token.getToken) {
        response(null, null);

        if (!this.globalData.inLoginPage) {
          this.globalData.inLoginPage = true;
          uni.navigateTo({
            url: "/pages/login/login",
          });
        }
      } else {
        console.log(path);
        uni.uploadFile({
          url: urls.file_upload,
          filePath: path,
          name: "uploadify",
          formData: {
            token: token.getToken,
          },

          success(res) {
            console.log(res);
            let dataStr = res.data;
            let data = JSON.parse(dataStr);
            let success = data.success;

            if (success) {
              response(data, null);
            } else {
              response(null, "success === false!");
            }
          },

          fail(e) {
            console.log(e);
            response(null, e.errMsg);
          },

          complete(res) {
            response(null, null);
          },
        });
      }
    },
    /**
     * 获取待上传图片的image实例数组
     */
    getImages: function (tag, paths) {
      let images = [];

      for (var j = 0; j < paths.length; j++) {
        images = images.concat({
          tag: tag,
          path: paths[j],
          fileid: null,
        });
      }

      return images;
    },
  },
};
</script>

<style lang="scss">
@import "uview-ui/index.scss";
// @import 'uview-ui/remixicon.css';
page {
  /* background: #f2f2f2; */
  width: 100%;
  height: 100%;
  background: #f2f2f2;
  // background: #EEF0FC;
}

view {
  box-sizing: border-box;
}
</style>