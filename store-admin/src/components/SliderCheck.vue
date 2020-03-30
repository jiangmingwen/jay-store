<template>
    <div class="slider-check">
        <div class="img-box" :style="{width:width+'px',height:height+'px'}"  >
            <img :style="{width:width+'px',height:height+'px'}" :src="imgData.modifyImg" alt="">
            <img :style="{
                width:puzzleWidth+'px',
                top: top+ 'px',
                left:left+'px',
                height:puzzHeight+'px',
             }"
              class="puzzle-img" :src="imgData.puzzleImg" alt="">
            <el-slider v-model="left" @change="onSlide" :max="width-puzzleWidth" :show-tooltip="false" />
        </div>
    </div>
</template>
<script>
export default {
  model: {
    prop: "value",
    event: "change"
  },
  props: {
    imgData: {
      type: Object,
      default: () => {}
    },
    value: {
      type: Number,
      default: 0
    },
    width: {
      type: Number,
      default: 500
    },
    height: {
      type: Number,
      default: 315
    }
  },
  data() {
    return {
      left: 0,
      xRelation: 1,
      yRelation: 1,
      top: 0,
      puzzleWidth: 55,
      puzzHeight: 45
    };
  },
  watch: {
    imgData: {
      handler(v) {
        console.log(v, "v");
        if (v.imgWidth) {
          this.xRelation = this.imgData.imgWidth / this.width;
          this.yRelation = this.imgData.imgHeight / this.height;
          this.top = this.imgData.puzzleYAxis / this.yRelation;
          this.puzzleWidth = this.imgData.puzzleWidth / this.xRelation;
          this.puzzHeight = this.imgData.puzzleHeight / this.yRelation;
          this.left = 0;
        }
      },
      immediate: true
    },
    value: {
      handler(v) {
        this.left = v / this.xRelation;
      },
      immediate: true
    }
  },
  methods: {
    onSlide(e) {
      this.$emit("change", e * this.xRelation);
    }
  }
};
</script>
<style lang="scss" scoped>
$--slider-height: 30px;
.slider-check {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: $--slider-height;
  .img-box {
    position: relative;
    .puzzle-img {
      position: absolute;
    }
  }
  /deep/ .el-slider {
    .el-slider__runway {
      &::after {
        content: ">向右滑动滑块 >";
        color: #d8d8d8;
        line-height: $--slider-height;
        z-index: 999;
        text-align: center;
        display: block;
        position: absolute;
        width: 100%;
      }

      height: $--slider-height;
      margin: 0;
      .el-slider__bar {
        height: $--slider-height;
      }
      .el-slider__button-wrapper {
        height: 100%;
        top: 0;
        .el-slider__button {
          height: 100%;
          border-radius: 3px;
          border: 0;
          content: ">>";
          cursor: move;
          background-repeat: no-repeat;
          background-position: center;
          background-color: #333;
        }
      }
    }
  }
}
</style>
