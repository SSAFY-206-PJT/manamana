"use strict";
exports.id = 865;
exports.ids = [865];
exports.modules = {

/***/ 5865:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (/* binding */ Headerbar)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(1853);
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_router__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var next_link__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(1664);
/* harmony import */ var next_link__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(next_link__WEBPACK_IMPORTED_MODULE_2__);

var __jsx = (react__WEBPACK_IMPORTED_MODULE_0___default().createElement);


function Headerbar(props) {
  var router = (0,next_router__WEBPACK_IMPORTED_MODULE_1__.useRouter)();
  var showBackBtn = props.showBackBtn;
  var pageName = props.pageName;
  var rightBtnOpt = props.rightBtn; // 왼쪽 버튼 (뒤로가기)

  var backBtn = // 왼쪽 : 뒤로가기, 오른쪽 : 편집 버튼인 경우
  showBackBtn && rightBtnOpt === 'EDIT' ? __jsx("div", {
    className: "h-6 w-12"
  }, __jsx("img", {
    src: "/images/HeaderBar_Back.png",
    alt: "goBack",
    onClick: function onClick() {
      return router.back();
    }
  })) : // 왼쪽 : 뒤로가기, 오른쪽 : 편집 버튼이 아닌 경우
  showBackBtn ? __jsx("div", {
    className: ""
  }, __jsx("img", {
    src: "/images/HeaderBar_Back.png",
    alt: "goBack",
    onClick: function onClick() {
      return router.back();
    }
  })) : // 왼쪽에 뒤로가기가 없는 경우
  __jsx("div", {
    className: "h-6 w-6"
  }); // 오른쪽 버튼 (알림, 편집, null)

  var showRightBtn = null;

  switch (rightBtnOpt) {
    case 'NOTI':
      // 알림
      showRightBtn = __jsx("div", null, __jsx((next_link__WEBPACK_IMPORTED_MODULE_2___default()), {
        href: "/notification"
      }, __jsx("img", {
        src: "/images/HeaderBar_Noti.png",
        alt: "notification"
      })));
      break;

    case 'EDIT':
      // 편집
      showRightBtn = __jsx("div", {
        className: "flex h-6 w-12 items-center justify-center rounded-md border-2 border-BackgroundLightComponentBolder text-sm"
      }, "\uD3B8\uC9D1");
      break;

    default:
      showRightBtn = __jsx("div", {
        className: "h-6 w-6"
      });
      break;
  }

  return __jsx("div", {
    className: "z-30 flex h-14 items-center justify-between bg-BackgroundLightComponent p-5"
  }, backBtn, __jsx("div", {
    className: ""
  }, __jsx("p", {
    className: "text-xl font-bold"
  }, pageName)), showRightBtn);
}

/***/ })

};
;