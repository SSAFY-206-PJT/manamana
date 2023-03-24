"use strict";
exports.id = 635;
exports.ids = [635];
exports.modules = {

/***/ 4635:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _mui_material__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(5692);
/* harmony import */ var _mui_material__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_mui_material__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _mui_material_styles__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(8442);
/* harmony import */ var _mui_material_styles__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_mui_material_styles__WEBPACK_IMPORTED_MODULE_2__);

var __jsx = (react__WEBPACK_IMPORTED_MODULE_0___default().createElement);



var theme = (0,_mui_material_styles__WEBPACK_IMPORTED_MODULE_2__.createTheme)({
  palette: {
    primary: {
      main: 'rgba(190, 52, 85, 1)'
    }
  }
});

function CommentInput(_ref) {
  var defaultValue = _ref.defaultValue,
      comment = _ref.comment;

  var _useState = (0,react__WEBPACK_IMPORTED_MODULE_0__.useState)(defaultValue.content),
      commentInput = _useState[0],
      setCommentInput = _useState[1];

  var _useState2 = (0,react__WEBPACK_IMPORTED_MODULE_0__.useState)(defaultValue.spoiler),
      spoilerInput = _useState2[0],
      setSpoilerInput = _useState2[1];

  var commentPost = function commentPost() {
    console.log(commentInput, spoilerInput);
    comment({
      content: commentInput,
      spoiler: spoilerInput
    });
    setCommentInput('');
    setSpoilerInput(false);
  };

  return __jsx("div", {
    className: "h-fit rounded border border-PrimaryLight bg-BackgroundLightComponent"
  }, __jsx("div", {
    className: "m-1 h-fit"
  }, __jsx(_mui_material_styles__WEBPACK_IMPORTED_MODULE_2__.ThemeProvider, {
    theme: theme
  }, __jsx(_mui_material__WEBPACK_IMPORTED_MODULE_1__.TextField, {
    fullWidth: true,
    id: "standard-multiline-flexible",
    color: "primary",
    multiline: true,
    value: commentInput,
    onChange: function onChange(e) {
      setCommentInput(e.target.value);
    },
    maxRows: 5,
    variant: "standard"
  }))), __jsx("div", {
    className: "flex items-center justify-between"
  }, __jsx("div", {
    className: "ml-1 flex"
  }, __jsx("div", {
    className: "flex items-center"
  }, __jsx("p", {
    className: "text-gray-400"
  }, "\uC2A4\uD3EC\uC77C\uB7EC\uAC00 \uD3EC\uD568")), __jsx(_mui_material__WEBPACK_IMPORTED_MODULE_1__.Switch, {
    checked: spoilerInput,
    onChange: function onChange(e) {
      setSpoilerInput(e.target.checked);
    }
  })), __jsx("div", {
    className: "flex items-center"
  }, __jsx("button", {
    className: "m-1 rounded-full bg-PrimaryLight py-1 px-4 font-semibold text-FontPrimaryDark",
    onClick: commentPost
  }, "\uB4F1\uB85D"))));
}

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (CommentInput);

/***/ })

};
;