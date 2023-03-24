"use strict";
exports.id = 619;
exports.ids = [619];
exports.modules = {

/***/ 1619:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "GY": () => (/* binding */ changeCurSearchTag),
/* harmony export */   "ZP": () => (__WEBPACK_DEFAULT_EXPORT__),
/* harmony export */   "lb": () => (/* binding */ deleteCurSearchOneTag)
/* harmony export */ });
/* harmony import */ var C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_helpers_esm_toConsumableArray_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(6687);
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(5184);
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__);


var initialState = {
  tags: []
};
var CurSearchTagSlice = (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.createSlice)({
  name: 'CurSearchTag',
  initialState: initialState,
  reducers: {
    changeCurSearchTag: function changeCurSearchTag(state, action) {
      state.tags = action.payload;
    },
    getCurSearchTag: function getCurSearchTag(state) {
      state.tags;
    },
    deleteCurSearchOneTag: function deleteCurSearchOneTag(state, action) {
      var newState = (0,C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_helpers_esm_toConsumableArray_js__WEBPACK_IMPORTED_MODULE_1__/* ["default"] */ .Z)(state.tags);

      for (var i = 0; i < newState.length; i++) {
        if (newState[i] === action.payload) {
          newState.splice(i, 1);
          break;
        }
      }

      state.tags = newState;
    }
  }
});
var _CurSearchTagSlice$ac = CurSearchTagSlice.actions,
    changeCurSearchTag = _CurSearchTagSlice$ac.changeCurSearchTag,
    deleteCurSearchOneTag = _CurSearchTagSlice$ac.deleteCurSearchOneTag;

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (CurSearchTagSlice);

/***/ })

};
;