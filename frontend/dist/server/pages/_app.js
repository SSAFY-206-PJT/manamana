(() => {
var exports = {};
exports.id = 888;
exports.ids = [888];
exports.modules = {

/***/ 4781:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "default": () => (/* binding */ _app)
});

// EXTERNAL MODULE: external "react"
var external_react_ = __webpack_require__(6689);
var external_react_default = /*#__PURE__*/__webpack_require__.n(external_react_);
// EXTERNAL MODULE: ./styles/globals.css
var globals = __webpack_require__(6764);
// EXTERNAL MODULE: ./node_modules/next/dist/compiled/@babel/runtime/helpers/esm/defineProperty.js
var defineProperty = __webpack_require__(9499);
// EXTERNAL MODULE: external "@reduxjs/toolkit"
var toolkit_ = __webpack_require__(5184);
;// CONCATENATED MODULE: external "next-redux-wrapper"
const external_next_redux_wrapper_namespaceObject = require("next-redux-wrapper");
// EXTERNAL MODULE: ./store/CurSearchTagSlice.ts
var CurSearchTagSlice = __webpack_require__(1619);
// EXTERNAL MODULE: ./store/GenreTasteSlice.ts
var GenreTasteSlice = __webpack_require__(3458);
;// CONCATENATED MODULE: ./store/index.ts


function ownKeys(object, enumerableOnly) {
  var keys = Object.keys(object);

  if (Object.getOwnPropertySymbols) {
    var symbols = Object.getOwnPropertySymbols(object);
    enumerableOnly && (symbols = symbols.filter(function (sym) {
      return Object.getOwnPropertyDescriptor(object, sym).enumerable;
    })), keys.push.apply(keys, symbols);
  }

  return keys;
}

function _objectSpread(target) {
  for (var i = 1; i < arguments.length; i++) {
    var source = null != arguments[i] ? arguments[i] : {};
    i % 2 ? ownKeys(Object(source), !0).forEach(function (key) {
      (0,defineProperty/* default */.Z)(target, key, source[key]);
    }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) {
      Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key));
    });
  }

  return target;
}






var RootReducer = function RootReducer(state, action) {
  if (action.type === external_next_redux_wrapper_namespaceObject.HYDRATE) return _objectSpread(_objectSpread({}, state), action.payload);
  var combinedReducer = (0,toolkit_.combineReducers)({
    searchTag: CurSearchTagSlice/* default.reducer */.ZP.reducer,
    genreTasteList: GenreTasteSlice/* default.reducer */.Z.reducer
  });
  return combinedReducer(state, action);
};

var makeStore = function makeStore() {
  return (0,toolkit_.configureStore)({
    reducer: RootReducer,
    devTools: false
  });
};

var wrapper = (0,external_next_redux_wrapper_namespaceObject.createWrapper)(makeStore);
;// CONCATENATED MODULE: ./pages/_app.tsx

var __jsx = (external_react_default()).createElement;



function App(_ref) {
  var Component = _ref.Component,
      pageProps = _ref.pageProps;
  return __jsx(Component, pageProps);
}

/* harmony default export */ const _app = (wrapper.withRedux(App));

/***/ }),

/***/ 3458:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "M": () => (/* binding */ changeGenreTaste),
/* harmony export */   "Z": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(5184);
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__);

var initialState = {
  genreTasteList: []
};
var GenreTasteSlice = (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.createSlice)({
  name: 'genreTasteList',
  initialState: initialState,
  reducers: {
    changeGenreTaste: function changeGenreTaste(state, action) {
      state.genreTasteList = action.payload;
      console.log(state.genreTasteList);
    }
  }
});
var changeGenreTaste = GenreTasteSlice.actions.changeGenreTaste;

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (GenreTasteSlice);

/***/ }),

/***/ 6764:
/***/ (() => {



/***/ }),

/***/ 5184:
/***/ ((module) => {

"use strict";
module.exports = require("@reduxjs/toolkit");

/***/ }),

/***/ 6689:
/***/ ((module) => {

"use strict";
module.exports = require("react");

/***/ }),

/***/ 9499:
/***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (/* binding */ _defineProperty)
/* harmony export */ });
function _defineProperty(obj, key, value) {
  if (key in obj) {
    Object.defineProperty(obj, key, {
      value: value,
      enumerable: true,
      configurable: true,
      writable: true
    });
  } else {
    obj[key] = value;
  }

  return obj;
}

/***/ })

};
;

// load runtime
var __webpack_require__ = require("../webpack-runtime.js");
__webpack_require__.C(exports);
var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
var __webpack_exports__ = __webpack_require__.X(0, [687,619], () => (__webpack_exec__(4781)));
module.exports = __webpack_exports__;

})();