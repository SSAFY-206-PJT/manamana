(() => {
var exports = {};
exports.id = 603;
exports.ids = [603];
exports.modules = {

/***/ 2859:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
var _path;
function _extends() { _extends = Object.assign ? Object.assign.bind() : function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; }; return _extends.apply(this, arguments); }

var SvgFiRsAngleSmallDown = function SvgFiRsAngleSmallDown(props) {
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createElement("svg", _extends({
    width: 20,
    height: 20,
    viewBox: "0 0 24 24",
    fill: "none",
    xmlns: "http://www.w3.org/2000/svg"
  }, props), _path || (_path = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createElement("path", {
    d: "M12 15.5a1.992 1.992 0 0 1-1.414-.585L5.293 9.621l1.414-1.414L12 13.5l5.293-5.293 1.414 1.414-5.293 5.293A1.992 1.992 0 0 1 12 15.5Z",
    fill: "#374957"
  })));
};
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (SvgFiRsAngleSmallDown);

/***/ }),

/***/ 3843:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (/* binding */ Navbar)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var next_link__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(1664);
/* harmony import */ var next_link__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_link__WEBPACK_IMPORTED_MODULE_1__);

var __jsx = (react__WEBPACK_IMPORTED_MODULE_0___default().createElement);

function Navbar() {
  return __jsx("div", {
    className: "fixed bottom-[-1px] left-0 right-0 flex w-screen bg-FooterBackground"
  }, __jsx("div", {
    className: "flex grow justify-center"
  }, __jsx((next_link__WEBPACK_IMPORTED_MODULE_1___default()), {
    href: "/"
  }, __jsx("img", {
    className: "p-3",
    src: "/images/NavBar_Home.png",
    alt: "GoHome"
  }))), __jsx("div", {
    className: "flex grow justify-center"
  }, __jsx((next_link__WEBPACK_IMPORTED_MODULE_1___default()), {
    href: "/search"
  }, __jsx("img", {
    className: "p-3",
    src: "/images/NavBar_Search.png",
    alt: "GoSearch"
  }))), __jsx("div", {
    className: "flex grow justify-center"
  }, __jsx((next_link__WEBPACK_IMPORTED_MODULE_1___default()), {
    href: "/profile"
  }, __jsx("img", {
    className: "p-3",
    src: "/images/NavBar_User.png",
    alt: "GoUser"
  }))));
}

/***/ }),

/***/ 5405:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (/* binding */ SearchBar)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var next_image__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(5675);
/* harmony import */ var next_image__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_image__WEBPACK_IMPORTED_MODULE_1__);

var __jsx = (react__WEBPACK_IMPORTED_MODULE_0___default().createElement);


function SearchBar(props) {
  var _useState = (0,react__WEBPACK_IMPORTED_MODULE_0__.useState)(""),
      searchContent = _useState[0],
      setSearchContent = _useState[1];
  /*
    * @Method
    * 입력이 변경됐을 경우 실행되는 메소드
    * */


  var onChangeSearchContent = function onChangeSearchContent(event) {
    setSearchContent(event.target.value);
    props.sendData(event.target.value);
    props.onSearchBarChange(event.target.value);
  };

  var onClickDeleteSearchContent = function onClickDeleteSearchContent(event) {
    setSearchContent('');
  };

  return __jsx("div", {
    className: "border-2 border-BackgroundLightComponentBorder rounded-2xl flex flex-row pl-4 pr-4 pt-1 pb-1 mb-4"
  }, __jsx("div", {
    className: "flex justify-center items-center"
  }, __jsx((next_image__WEBPACK_IMPORTED_MODULE_1___default()), {
    src: '/images/search-img.svg',
    alt: "search bar logo",
    width: 20,
    height: 20
  })), __jsx("input", {
    className: "ml-4 w-full outline-none",
    placeholder: "\uC6F9\uD230 \uAC80\uC0C9",
    onChange: onChangeSearchContent,
    value: searchContent
  }), searchContent.length > 0 ? __jsx("div", {
    className: "flex top-1 justify-center items-center",
    onClick: onClickDeleteSearchContent
  }, __jsx((next_image__WEBPACK_IMPORTED_MODULE_1___default()), {
    alt: "delete content",
    src: '/images/Delete_Gray_Circle.png',
    width: 20,
    height: 20
  })) : __jsx("div", null));
}

/***/ }),

/***/ 2398:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (/* binding */ SearchTag)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var next_image__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(5675);
/* harmony import */ var next_image__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_image__WEBPACK_IMPORTED_MODULE_1__);

var __jsx = (react__WEBPACK_IMPORTED_MODULE_0___default().createElement);

function SearchTag(props) {
  var ondeleteTagClick = function ondeleteTagClick() {
    props.deleteTag(props.tagName);
  };

  return __jsx("div", {
    className: "flex justify-center items-center gap-2 bg-PrimaryLight text-FontPrimaryDark pl-2 pr-2 pt-1 pb-1 rounded-xl"
  }, __jsx("div", {
    className: "inline-block"
  }, props.tagName), __jsx("div", {
    className: "inline-block",
    onClick: ondeleteTagClick
  }, __jsx((next_image__WEBPACK_IMPORTED_MODULE_1___default()), {
    src: '/images/Delete_White.png',
    alt: "delete tag",
    width: 8,
    height: 8
  })));
}
;

/***/ }),

/***/ 7025:
/***/ ((module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.a(module, async (__webpack_handle_async_dependencies__, __webpack_async_result__) => { try {
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (/* binding */ SearchPage),
/* harmony export */   "getServerSideProps": () => (/* binding */ getServerSideProps)
/* harmony export */ });
/* harmony import */ var C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_helpers_esm_asyncToGenerator_js__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(29);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6689);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_regenerator_index_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(7794);
/* harmony import */ var C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_regenerator_index_js__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_regenerator_index_js__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _components_common_Navbar__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(3843);
/* harmony import */ var _components_common_Headerbar__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(5865);
/* harmony import */ var _components_pages_search_SearchBar__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(5405);
/* harmony import */ var next_image__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(5675);
/* harmony import */ var next_image__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(next_image__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _public_images_fi_rs_angle_small_down_svg__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(2859);
/* harmony import */ var next_link__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(1664);
/* harmony import */ var next_link__WEBPACK_IMPORTED_MODULE_7___default = /*#__PURE__*/__webpack_require__.n(next_link__WEBPACK_IMPORTED_MODULE_7__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(6022);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_8___default = /*#__PURE__*/__webpack_require__.n(react_redux__WEBPACK_IMPORTED_MODULE_8__);
/* harmony import */ var _store_CurSearchTagSlice__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(1619);
/* harmony import */ var _components_pages_search_SearchTag__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(2398);
/* harmony import */ var react_lottie_player__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(9094);
/* harmony import */ var react_lottie_player__WEBPACK_IMPORTED_MODULE_11___default = /*#__PURE__*/__webpack_require__.n(react_lottie_player__WEBPACK_IMPORTED_MODULE_11__);
/* harmony import */ var _public_lottie_51382_astronaut_light_theme_json__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(1333);
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(9648);
/* harmony import */ var _components_common_WebtoonItem__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(1338);
var __webpack_async_dependencies__ = __webpack_handle_async_dependencies__([axios__WEBPACK_IMPORTED_MODULE_13__]);
axios__WEBPACK_IMPORTED_MODULE_13__ = (__webpack_async_dependencies__.then ? (await __webpack_async_dependencies__)() : __webpack_async_dependencies__)[0];


var __jsx = (react__WEBPACK_IMPORTED_MODULE_0___default().createElement);















function SearchPage(props) {
  var dispatch = (0,react_redux__WEBPACK_IMPORTED_MODULE_8__.useDispatch)();
  var curSearchTag = (0,react_redux__WEBPACK_IMPORTED_MODULE_8__.useSelector)(function (state) {
    return state.searchTag;
  }); // Redux에 있는 값

  var _useState = (0,react__WEBPACK_IMPORTED_MODULE_0__.useState)(null),
      selectedTagListElement = _useState[0],
      setSelectedTagListElements = _useState[1]; // Tag 정보를 이용하여 Element로 변환한 값 


  var _useState2 = (0,react__WEBPACK_IMPORTED_MODULE_0__.useState)([]),
      webtoonList = _useState2[0],
      setWebtoonList = _useState2[1]; // 웹툰 정보 리스트


  var _useState3 = (0,react__WEBPACK_IMPORTED_MODULE_0__.useState)(null),
      webtoonListElement = _useState3[0],
      setWebtoonListElement = _useState3[1]; // 웹툰 정보를 이용하여 Element로 변환한 값

  /*
  * @Method
  * 필터가 클릭됐을 경우 실행되는 메소드
  * */


  var onfilterClick = function onfilterClick() {
    console.log("필터 클릭됨");
  };
  /*
  * @Method
  * 배치 순서 버튼이 클릭됐을 경우 실행되는 메소드
  * */


  var onOrderButtonClick = function onOrderButtonClick() {
    alert("배치 순서 버튼 클릭됨");
  };
  /*
  * @Method
  * Seach Bar에 변화가 있을 시 서버와 통신할 수 있도록 호출되는 메소드
  * */


  var changeSearchContent = function changeSearchContent(content) {};
  /*
  * @Method
  * Tag 삭제 버튼 클릭시 태그 삭제
  * */


  var deleteTag = function deleteTag(value) {
    dispatch((0,_store_CurSearchTagSlice__WEBPACK_IMPORTED_MODULE_9__/* .deleteCurSearchOneTag */ .lb)(value));
    reloadTag();
  };

  var reloadTag = function reloadTag() {
    return setSelectedTagListElements(curSearchTag.tags.map(function (v) {
      return __jsx(_components_pages_search_SearchTag__WEBPACK_IMPORTED_MODULE_10__/* ["default"] */ .Z, {
        tagName: v,
        deleteTag: deleteTag
      });
    }));
  };

  var onSearchBarChange = function onSearchBarChange(message) {};

  (0,react__WEBPACK_IMPORTED_MODULE_0__.useEffect)(function () {
    reloadTag();
    console.log(props.data);
    setWebtoonList(props.data);
  }, []);
  (0,react__WEBPACK_IMPORTED_MODULE_0__.useEffect)(function () {
    reloadTag();
  }, [curSearchTag.tags]);
  (0,react__WEBPACK_IMPORTED_MODULE_0__.useEffect)(function () {
    setWebtoonListElement(webtoonList.map(function (data) {
      return __jsx(_components_common_WebtoonItem__WEBPACK_IMPORTED_MODULE_14__/* ["default"] */ .Z, {
        imageUrl: data.imagePath,
        status: data.status,
        webtoonName: data.name,
        key: data.id
      });
    }));
  }, [webtoonList]);
  return __jsx("div", {
    className: "bg-BackgroundLight h-screen"
  }, __jsx(_components_common_Headerbar__WEBPACK_IMPORTED_MODULE_3__/* ["default"] */ .Z, {
    showBackBtn: true,
    pageName: "\uD0D0\uC0C9",
    rightBtn: "EDIT"
  }), __jsx("div", {
    className: "bg-BackgroundLightComponent m-2 p-4 pb-2 rounded-2xl"
  }, __jsx(_components_pages_search_SearchBar__WEBPACK_IMPORTED_MODULE_4__/* ["default"] */ .Z, {
    sendData: changeSearchContent,
    onSearchBarChange: onSearchBarChange
  }), __jsx("div", {
    className: "flex flex-row justify-between items-center"
  }, __jsx("div", {
    className: "font-bold text-xl pl-2 pr-2"
  }, __jsx("span", null, "\uC804\uCCB4"), __jsx("span", {
    className: "ml-1 text-PrimaryLight"
  }, webtoonList.length > 999 ? '999+' : webtoonList.length), __jsx("span", {
    className: "ml-1"
  }, "\uAC1C")), __jsx("div", {
    className: "flex flex-row mr-2"
  }, __jsx("button", {
    className: "mr-2 flex border-2 pl-2 rounded-2xl border-BackgroundLightComponentBorder",
    onClick: onOrderButtonClick
  }, __jsx("span", null, "\uC870\uD68C\uC21C"), __jsx(_public_images_fi_rs_angle_small_down_svg__WEBPACK_IMPORTED_MODULE_6__/* ["default"] */ .Z, {
    width: 20,
    height: 20
  })), __jsx((next_link__WEBPACK_IMPORTED_MODULE_7___default()), {
    href: "/search/filter"
  }, __jsx((next_image__WEBPACK_IMPORTED_MODULE_5___default()), {
    src: '/images/filter-img.svg',
    alt: "filter",
    width: "20",
    height: "20",
    className: "ml-2",
    onClick: onfilterClick
  })))), __jsx("div", {
    className: "flex flex- row flex-wrap gap-2 m-2"
  }, selectedTagListElement)), webtoonList.length == 0 ? __jsx("div", {
    className: "w-full h-2/3 flex flex-col justify-center items-center"
  }, __jsx((react_lottie_player__WEBPACK_IMPORTED_MODULE_11___default()), {
    loop: true,
    animationData: _public_lottie_51382_astronaut_light_theme_json__WEBPACK_IMPORTED_MODULE_12__,
    play: true,
    className: "w-2/3 h-2/3"
  })) : __jsx("div", {
    className: "bg-BackgroundLightComponent m-2 p-4 rounded-2xl"
  }, webtoonListElement), __jsx(_components_common_Navbar__WEBPACK_IMPORTED_MODULE_2__/* ["default"] */ .Z, null));
}
function getServerSideProps() {
  return _getServerSideProps.apply(this, arguments);
}

function _getServerSideProps() {
  _getServerSideProps = (0,C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_helpers_esm_asyncToGenerator_js__WEBPACK_IMPORTED_MODULE_15__/* ["default"] */ .Z)( /*#__PURE__*/C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_regenerator_index_js__WEBPACK_IMPORTED_MODULE_1___default().mark(function _callee() {
    var response;
    return C_Users_SSAFY_Desktop_S08P22B206_frontend_node_modules_next_dist_compiled_babel_runtime_regenerator_index_js__WEBPACK_IMPORTED_MODULE_1___default().wrap(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            _context.next = 2;
            return axios__WEBPACK_IMPORTED_MODULE_13__["default"].get("https://j8b206.p.ssafy.io/api/webtoons");

          case 2:
            response = _context.sent;
            return _context.abrupt("return", {
              props: {
                data: response.data.result
              }
            });

          case 4:
          case "end":
            return _context.stop();
        }
      }
    }, _callee);
  }));
  return _getServerSideProps.apply(this, arguments);
}
__webpack_async_result__();
} catch(e) { __webpack_async_result__(e); } });

/***/ }),

/***/ 7794:
/***/ ((module, __unused_webpack_exports, __webpack_require__) => {

module.exports = __webpack_require__(8319);


/***/ }),

/***/ 5184:
/***/ ((module) => {

"use strict";
module.exports = require("@reduxjs/toolkit");

/***/ }),

/***/ 8319:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/compiled/regenerator-runtime");

/***/ }),

/***/ 3918:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/amp-context.js");

/***/ }),

/***/ 5732:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/amp-mode.js");

/***/ }),

/***/ 3280:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/app-router-context.js");

/***/ }),

/***/ 2796:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/head-manager-context.js");

/***/ }),

/***/ 4486:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/image-blur-svg.js");

/***/ }),

/***/ 744:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/image-config-context.js");

/***/ }),

/***/ 5843:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/image-config.js");

/***/ }),

/***/ 9552:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/image-loader");

/***/ }),

/***/ 4964:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router-context.js");

/***/ }),

/***/ 1751:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router/utils/add-path-prefix.js");

/***/ }),

/***/ 3938:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router/utils/format-url.js");

/***/ }),

/***/ 1109:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router/utils/is-local-url.js");

/***/ }),

/***/ 8854:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router/utils/parse-path.js");

/***/ }),

/***/ 3297:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router/utils/remove-trailing-slash.js");

/***/ }),

/***/ 7782:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/router/utils/resolve-href.js");

/***/ }),

/***/ 2470:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/side-effect.js");

/***/ }),

/***/ 9232:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/utils.js");

/***/ }),

/***/ 618:
/***/ ((module) => {

"use strict";
module.exports = require("next/dist/shared/lib/utils/warn-once.js");

/***/ }),

/***/ 1853:
/***/ ((module) => {

"use strict";
module.exports = require("next/router");

/***/ }),

/***/ 6689:
/***/ ((module) => {

"use strict";
module.exports = require("react");

/***/ }),

/***/ 9094:
/***/ ((module) => {

"use strict";
module.exports = require("react-lottie-player");

/***/ }),

/***/ 6022:
/***/ ((module) => {

"use strict";
module.exports = require("react-redux");

/***/ }),

/***/ 9648:
/***/ ((module) => {

"use strict";
module.exports = import("axios");;

/***/ }),

/***/ 29:
/***/ ((__unused_webpack___webpack_module__, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "Z": () => (/* binding */ _asyncToGenerator)
/* harmony export */ });
function asyncGeneratorStep(gen, resolve, reject, _next, _throw, key, arg) {
  try {
    var info = gen[key](arg);
    var value = info.value;
  } catch (error) {
    reject(error);
    return;
  }

  if (info.done) {
    resolve(value);
  } else {
    Promise.resolve(value).then(_next, _throw);
  }
}

function _asyncToGenerator(fn) {
  return function () {
    var self = this,
        args = arguments;
    return new Promise(function (resolve, reject) {
      var gen = fn.apply(self, args);

      function _next(value) {
        asyncGeneratorStep(gen, resolve, reject, _next, _throw, "next", value);
      }

      function _throw(err) {
        asyncGeneratorStep(gen, resolve, reject, _next, _throw, "throw", err);
      }

      _next(undefined);
    });
  };
}

/***/ }),

/***/ 1333:
/***/ ((module) => {

"use strict";
module.exports = JSON.parse('{"v":"5.7.1","fr":60,"ip":0,"op":360,"w":231,"h":95,"nm":"astronautTamKemikli","ddd":0,"assets":[{"id":"comp_0","layers":[{"ddd":0,"ind":1,"ty":4,"nm":"yildizlar Outlines","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[120.277,45.662,0],"ix":2},"a":{"a":0,"k":[103.779,38.709,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[4.932,-4.565]],"o":[[-7.53,3.723],[0,0]],"v":[[14.143,-16.486],[-6.643,-3.014]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"st","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":1,"ix":5},"lc":2,"lj":1,"ml":10,"bm":0,"d":[{"n":"d","nm":"dash","v":{"a":0,"k":25,"ix":1}},{"n":"o","nm":"offset","v":{"a":1,"k":[{"i":{"x":[0.472],"y":[1]},"o":{"x":[0.287],"y":[0]},"t":111,"s":[-25]},{"t":153,"s":[-75]}],"ix":7}}],"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[69.562,91.92],"ix":2},"a":{"a":0,"k":[35.88,45.173],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.73],"y":[0]},"t":105,"s":[0]},{"t":178,"s":[-24.595]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.793,0],[0,-0.793],[0.793,0],[0,0.793]],"o":[[0.793,0],[0,0.793],[-0.793,0],[0,-0.793]],"v":[[0,-1.436],[1.436,0],[0,1.436],[-1.436,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[21.939,70.597],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":65,"s":[0,0]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":91,"s":[100,100]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":96,"s":[100,100]},{"t":122,"s":[0,0]}],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":61,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":4,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.574,0],[0,-0.575],[0.575,0],[0,0.575]],"o":[[0.575,0],[0,0.575],[-0.574,0],[0,-0.575]],"v":[[0,-1.041],[1.041,0],[0,1.041],[-1.041,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[206.267,1.291],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":223,"s":[0,0]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":249,"s":[100,100]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":254,"s":[100,100]},{"t":280,"s":[0,0]}],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":61,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 3","np":4,"cix":2,"bm":0,"ix":3,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.851,0],[0,-0.851],[0.852,0],[0,0.851]],"o":[[0.852,0],[0,0.851],[-0.851,0],[0,-0.851]],"v":[[0,-1.541],[1.541,0],[0,1.541],[-1.541,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[172.767,24.602],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":21,"s":[0,0]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":47,"s":[100,100]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":52,"s":[100,100]},{"t":78,"s":[0,0]}],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":61,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 4","np":4,"cix":2,"bm":0,"ix":4,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.851,0],[0,-0.851],[0.851,0],[0,0.851]],"o":[[0.851,0],[0,0.851],[-0.851,0],[0,-0.851]],"v":[[0,-1.541],[1.541,0],[0,1.541],[-1.541,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[35.767,12.602],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":134,"s":[0,0]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":160,"s":[100,100]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":165,"s":[100,100]},{"t":191,"s":[0,0]}],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":61,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 5","np":4,"cix":2,"bm":0,"ix":5,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],"o":[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],"v":[[-0.001,-3.536],[1.249,-1.25],[3.535,0],[1.249,1.25],[-0.001,3.536],[-1.251,1.25],[-3.536,0],[-1.251,-1.25]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[192.937,73.633],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":105,"s":[0,0]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":135,"s":[100,100]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":150,"s":[100,100]},{"t":180,"s":[0,0]}],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":105,"s":[0]},{"t":180,"s":[180]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 6","np":2,"cix":2,"bm":0,"ix":6,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],"o":[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]],"v":[[0,-3.456],[1.222,-1.222],[3.456,0],[1.222,1.222],[0,3.456],[-1.222,1.222],[-3.456,0],[-1.222,-1.222]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[3.706,28.971],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":285,"s":[0,0]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":315,"s":[100,100]},{"i":{"x":[0.667,0.667],"y":[1,1]},"o":{"x":[0.333,0.333],"y":[0,0]},"t":330,"s":[100,100]},{"t":359,"s":[0,0]}],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":285,"s":[0]},{"t":359,"s":[180]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 7","np":2,"cix":2,"bm":0,"ix":7,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0}]},{"id":"comp_1","layers":[{"ddd":0,"ind":1,"ty":4,"nm":"yildizlar Outlines","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[113.895,47.73,0],"ix":2},"a":{"a":0,"k":[100.367,31.507,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.793,0],[0,-0.793],[0.793,0],[0,0.793]],"o":[[0.793,0],[0,0.793],[-0.793,0],[0,-0.793]],"v":[[0,-1.436],[1.436,0],[0,1.436],[-1.436,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[3.332,61.327],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[100]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[50]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[100]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[50]},{"t":359,"s":[100]}],"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":2,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.575,0],[0,-0.575],[0.575,0],[0,0.575]],"o":[[0.575,0],[0,0.575],[-0.575,0],[0,-0.575]],"v":[[0,-1.041],[1.041,0],[0,1.041],[-1.041,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[41.319,43.181],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[65]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[100]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[65]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[100]},{"t":359,"s":[65]}],"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":2,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.574,0],[0,-0.575],[0.575,0],[0,0.575]],"o":[[0.575,0],[0,0.575],[-0.574,0],[0,-0.575]],"v":[[0,-1.041],[1.041,0],[0,1.041],[-1.041,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[199.442,13.054],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[30]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[50]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[30]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[50]},{"t":359,"s":[30]}],"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 3","np":2,"cix":2,"bm":0,"ix":3,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.851,0],[0,-0.852],[0.852,0],[0,0.851]],"o":[[0.852,0],[0,0.851],[-0.851,0],[0,-0.852]],"v":[[0,-1.541],[1.54,0],[0,1.541],[-1.54,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[179.484,53.659],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[81]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[100]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[81]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[100]},{"t":359,"s":[81]}],"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 4","np":2,"cix":2,"bm":0,"ix":4,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.851,0],[0,-0.851],[0.852,0],[0,0.851]],"o":[[0.852,0],[0,0.851],[-0.851,0],[0,-0.851]],"v":[[0,-1.541],[1.54,0],[0,1.541],[-1.54,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[165.524,1.791],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[60]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[100]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[60]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[100]},{"t":359,"s":[60]}],"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 5","np":2,"cix":2,"bm":0,"ix":5,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.851,0],[0,-0.851],[0.851,0],[0,0.851]],"o":[[0.851,0],[0,0.851],[-0.851,0],[0,-0.851]],"v":[[0,-1.541],[1.541,0],[0,1.541],[-1.541,0]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.685999971278,0.709999952129,0.736999990426,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[1.791,4.873],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[85]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[100]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[85]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[100]},{"t":359,"s":[85]}],"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 6","np":2,"cix":2,"bm":0,"ix":6,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0}]}],"layers":[{"ddd":0,"ind":1,"ty":4,"nm":"onBilek Outlines","parent":4,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[33.999,1.729,0],"ix":2},"a":{"a":0,"k":[2.632,8.25,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-0.391,0.253],[-1.228,1.469],[0.014,-1.873],[-0.739,-0.312],[1.479,0.012],[0.614,-0.323],[1.009,-1.119],[0.34,0.326],[0.037,0.06]],"o":[[-0.249,-0.393],[1.634,-0.999],[1.59,-1.976],[-0.013,1.637],[0.882,0.373],[-0.752,-0.006],[-0.613,0.323],[-0.326,0.34],[-0.05,-0.049],[0,0]],"v":[[-5.258,1.368],[-5.004,0.202],[-0.682,-3.523],[3.867,-2.542],[4.626,-0.113],[3.882,1.898],[1.777,2.225],[-1.644,5.15],[-2.85,5.173],[-2.982,5.01]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[5.757,5.75],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":2,"ty":4,"nm":"onParkmakAlt Outlines","parent":1,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":76,"s":[-13.21]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":170,"s":[8.158]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":264,"s":[-26.116]},{"t":359,"s":[0]}],"ix":10},"p":{"a":0,"k":[9.937,6.549,0],"ix":2},"a":{"a":0,"k":[1.542,1.443,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.567,-0.281],[0.506,-0.695],[0.559,0],[-0.341,0.646]],"o":[[1.621,0.801],[-0.381,0.525],[-0.637,0.001],[0.229,-0.437]],"v":[[-0.71,-0.975],[1.942,0.731],[-1.347,1.037],[-2.107,-0.444]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[2.698,1.506],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":3,"ty":4,"nm":"onParmakUst Outlines","parent":1,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":76,"s":[22.093]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":170,"s":[-11.458]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":264,"s":[22.093]},{"t":359,"s":[0]}],"ix":10},"p":{"a":0,"k":[7.276,3.344,0],"ix":2},"a":{"a":0,"k":[3.33,6.926,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.944,1.734],[-0.828,-0.129],[-0.001,-2.134],[1.381,-0.094]],"o":[[0.944,-1.734],[0.8,0.125],[0,0.936],[-1.449,0.098]],"v":[[-1.854,1.104],[1.998,-4.328],[2.628,2.252],[0.25,4.359]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[3.049,4.707],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":4,"ty":4,"nm":"onKol Outlines","parent":7,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[16]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[-6]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[16]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[-6]},{"t":359,"s":[16]}],"ix":10},"p":{"a":0,"k":[18.105,29.056,0],"ix":2},"a":{"a":0,"k":[8.595,21.521,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-0.008,0.309],[0,0],[0,0],[4.097,-2.491],[0.396,-2.433],[-3.825,-2.121]],"o":[[0,0],[0,0],[0,0],[0,0],[-1.449,0.88],[-0.238,1.46],[6.975,3.868]],"v":[[-1.18,10.577],[7.943,-0.391],[17.094,-11.792],[14.423,-15.771],[-14.09,-1.361],[-16.857,3.628],[-13.135,11.904]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.635000011968,0.670999983245,0.705999995213,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[17.345,16.021],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":5,"ty":4,"nm":"onAyakKalca Outlines","parent":7,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[0.992]},"o":{"x":[0.412],"y":[0]},"t":0,"s":[-5]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[-0.009]},"t":167,"s":[10]},{"t":359,"s":[-5]}],"ix":10},"p":{"a":0,"k":[28.32,55.049,0],"ix":2},"a":{"a":0,"k":[12.912,15.937,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[2.842,0.331],[3.03,-0.355],[4.888,-2.598],[-0.979,-1.61],[-4.734,1.638],[-7.77,1.233],[0,4.341]],"o":[[-5.686,-0.663],[-3.03,0.357],[-8.171,4.34],[3.534,5.812],[7.485,-2.589],[3.884,-0.615],[0,-4.085]],"v":[[16.911,-12.773],[1.656,-12.585],[-15.617,-7.764],[-20.494,6.419],[-7.156,11.799],[16.721,2.008],[23.788,-5.542]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.635000011968,0.670999983245,0.705999995213,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[24.037,13.687],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":6,"ty":4,"nm":"onAyakUC Outlines","parent":5,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.713],"y":[1]},"o":{"x":[0.484],"y":[0.113]},"t":0,"s":[0]},{"i":{"x":[0.564],"y":[1]},"o":{"x":[0.286],"y":[0]},"t":167,"s":[32]},{"t":360,"s":[0]}],"ix":10},"p":{"a":0,"k":[38.83,8.344,0],"ix":2},"a":{"a":0,"k":[8.234,7.999,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[1.187,0.838],[0,0],[0.174,-0.246],[0,0],[0,0],[-0.246,-0.175],[0,0],[-0.976,1.377],[0,0],[0,0],[0,0],[1.155,0.818],[0,0],[0.869,-0.506],[0,0]],"o":[[0,0],[-0.245,-0.174],[0,0],[0,0],[-0.174,0.246],[0,0],[1.377,0.974],[0,0],[0,0],[0,0],[0.82,-1.156],[0,-0.001],[-0.819,-0.582],[0,0],[-1.256,0.73]],"v":[[-1.027,-5.874],[-4.165,-8.096],[-4.925,-7.967],[-4.926,-7.965],[-10.856,0.406],[-10.726,1.167],[-1.826,7.469],[2.434,6.74],[6.349,1.214],[7.229,-0.027],[10.21,-4.238],[9.602,-7.812],[9.601,-7.813],[6.829,-7.937],[2.982,-5.697]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[32.689,17.305],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[5.685,0.662],[0.683,-5.809],[-4.046,0.641],[0,0],[0,0]],"o":[[-2.726,-0.318],[-0.656,5.568],[7.771,-1.233],[0,0],[0,0]],"v":[[-3.912,-8.469],[-13.358,-1.73],[-5.302,6.593],[7.76,8.787],[14.014,0.449]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.635000011968,0.670999983245,0.705999995213,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[14.264,9.037],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":4,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":7,"ty":4,"nm":"govde Outlines","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0.092]},"t":0,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.335],"y":[0]},"t":90,"s":[-4]},{"i":{"x":[0.735],"y":[0.855]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[4]},{"t":359,"s":[0]}],"ix":10},"p":{"a":1,"k":[{"i":{"x":0.667,"y":1},"o":{"x":0.275,"y":0.402},"t":0,"s":[107.641,52.249,0],"to":[0,0.958,0],"ti":[0,0.708,0]},{"i":{"x":0.667,"y":1},"o":{"x":0.333,"y":0},"t":105,"s":[107.641,57.999,0],"to":[0,-0.708,0],"ti":[0,0.958,0]},{"i":{"x":0.725,"y":0.482},"o":{"x":0.323,"y":0},"t":255,"s":[107.641,47.999,0],"to":[0,-0.958,0],"ti":[0,-0.708,0]},{"t":359,"s":[107.641,52.249,0]}],"ix":2},"a":{"a":0,"k":[28.921,41.814,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":1,"k":[{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":0,"s":[{"i":[[12.562,10.319],[1.021,4.886],[0,0],[2.176,2.871],[4.097,-2.491],[-4.076,-13.929],[-7.484,2.59]],"o":[[-3.522,-2.894],[-0.067,-0.309],[0,0],[-2.993,-3.95],[-4.023,2.444],[4.074,13.929],[7.486,-2.589]],"v":[[9.704,-3.18],[2.873,-13.072],[-5.333,-9.365],[-6.223,-19.564],[-18.243,-21.303],[-18.15,3.643],[0.893,21.204]],"c":true}]},{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":90,"s":[{"i":[[12.562,10.319],[1.021,4.886],[0,0],[2.176,2.871],[4.097,-2.491],[-4.076,-13.929],[-7.484,2.59]],"o":[[-3.522,-2.894],[-0.067,-0.309],[0,0],[-2.993,-3.95],[-4.023,2.444],[4.074,13.929],[7.486,-2.589]],"v":[[9.704,-3.18],[1.29,-16.023],[-5.333,-9.365],[-6.223,-19.564],[-18.243,-21.303],[-18.15,3.643],[0.893,21.204]],"c":true}]},{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":180,"s":[{"i":[[12.562,10.319],[1.021,4.886],[0,0],[2.176,2.871],[4.097,-2.491],[-4.076,-13.929],[-7.484,2.59]],"o":[[-3.522,-2.894],[-0.067,-0.309],[0,0],[-2.993,-3.95],[-4.023,2.444],[4.074,13.929],[7.486,-2.589]],"v":[[9.704,-3.18],[2.873,-13.072],[-5.333,-9.365],[-6.223,-19.564],[-18.243,-21.303],[-18.15,3.643],[0.893,21.204]],"c":true}]},{"i":{"x":0.833,"y":0.833},"o":{"x":0.167,"y":0.167},"t":270,"s":[{"i":[[12.562,10.319],[1.021,4.886],[0,0],[2.176,2.871],[4.097,-2.491],[-4.076,-13.929],[-7.484,2.59]],"o":[[-3.522,-2.894],[-0.067,-0.309],[0,0],[-2.993,-3.95],[-4.023,2.444],[4.074,13.929],[7.486,-2.589]],"v":[[9.704,-3.18],[1.29,-16.023],[-5.333,-9.365],[-6.223,-19.564],[-18.243,-21.303],[-18.15,3.643],[0.893,21.204]],"c":true}]},{"t":359,"s":[{"i":[[12.562,10.319],[1.021,4.886],[0,0],[2.176,2.871],[4.097,-2.491],[-4.076,-13.929],[-7.484,2.59]],"o":[[-3.522,-2.894],[-0.067,-0.309],[0,0],[-2.993,-3.95],[-4.023,2.444],[4.074,13.929],[7.486,-2.589]],"v":[[9.704,-3.18],[2.873,-13.072],[-5.333,-9.365],[-6.223,-19.564],[-18.243,-21.303],[-18.15,3.643],[0.893,21.204]],"c":true}]}],"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.635000011968,0.670999983245,0.705999995213,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[31.036,43.49],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-0.391,-0.526],[-0.26,-0.588],[-0.117,-0.629],[0.011,-0.635],[0.079,-0.003],[0.002,0.079],[0,0],[0.143,0.595],[0.782,0.929],[0.483,0.344],[0,0],[-0.101,0.151],[-0.151,-0.101],[-0.003,-0.002]],"o":[[0.517,0.403],[0.391,0.511],[0.263,0.584],[0.118,0.624],[0.002,0.079],[-0.078,0.002],[0,0],[-0.02,-0.611],[-0.286,-1.18],[-0.377,-0.457],[0,0],[-0.151,-0.1],[0.101,-0.151],[0.002,0.002],[0,0]],"v":[[-1.257,-3.404],[0.112,-2.004],[1.092,-0.349],[1.664,1.476],[1.825,3.369],[1.687,3.516],[1.541,3.378],[1.541,3.369],[1.295,1.556],[-0.329,-1.651],[-1.624,-2.856],[-1.644,-2.87],[-1.735,-3.326],[-1.279,-3.417],[-1.271,-3.412]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[1,1,1,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[25.205,7.727],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[1.933,-0.01],[0.002,1.937],[-1.436,0.476],[-0.375,0.002],[0,-1.933]],"o":[[-0.004,1.933],[-1.937,0.002],[-0.001,-1.513],[0.357,-0.116],[1.933,0],[0,0]],"v":[[3.506,-0.005],[0.005,3.506],[-3.505,0.002],[-1.101,-3.33],[0.005,-3.508],[3.506,-0.008]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.783999992819,0.808000033509,0.838999968884,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[17.804,12.122],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 3","np":4,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0.08,-0.23],[0,0],[0.13,-0.26],[0,0],[0,0],[0.35,0.09],[0,0],[0.04,2.14],[-0.23,0.58],[-0.01,0],[0,0],[-0.09,0.16],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[-0.01,-0.55],[-1.69,0.04],[0,0],[0,0],[0,0]],"o":[[-0.06,0.24],[0,0],[-0.1,0.27],[0,0],[0,0],[-0.38,0],[0,0],[-1.98,-0.49],[-0.01,-0.62],[0,0],[0,0],[0.06,-0.17],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[-0.26,0.48],[0.04,1.73],[0,0],[0,0],[0,0],[0,0]],"v":[[6.19,5.76],[5.97,6.47],[5.95,6.47],[5.62,7.27],[-1.46,7.41],[-1.56,7.41],[-2.65,7.27],[-2.67,7.27],[-6.18,2.87],[-5.84,1.05],[-5.83,1.04],[-5.83,1.03],[-5.6,0.54],[-5.53,0.42],[-1.22,-7.41],[-0.42,-7.15],[-0.41,-7.15],[-0.08,-6.97],[-0.06,-6.96],[0.17,-6.83],[-3.9,0.57],[-4.29,1.27],[-4.68,2.84],[-1.48,5.91],[-1.38,5.91],[-1.1,5.9],[6.18,5.76]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.783999992819,0.808000033509,0.838999968884,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[21.883,8.05],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 4","np":4,"cix":2,"bm":0,"ix":3,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,-0.27],[0,0],[0.03,-0.28],[0.04,-0.24],[0.12,-0.42],[0.08,-0.23],[0,0],[0,0],[0,0],[0,0],[0,0],[0.04,2.14],[-0.33,0.6],[0,0],[0,0],[0,0],[-0.23,-0.1],[0,0],[-0.33,-4]],"o":[[0,0],[0,0.28],[-0.01,0.23],[-0.06,0.43],[-0.06,0.24],[0,0],[0,0],[0,0],[0,0],[0,0],[-2.14,0.04],[-0.02,-0.69],[0,0],[0,0],[0,0],[0.24,0.08],[0,0],[3.73,1.51],[0.02,0.26]],"v":[[6.165,3.135],[6.165,3.235],[6.125,4.065],[6.045,4.775],[5.775,6.045],[5.555,6.755],[5.535,6.755],[3.645,6.795],[-2.095,6.905],[-2.105,6.905],[-2.185,6.905],[-6.145,3.105],[-5.665,1.145],[-5.595,1.025],[-2.855,-3.965],[-1.205,-6.945],[-0.495,-6.685],[-0.475,-6.675],[6.135,2.335]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[22.298,7.765],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 5","np":4,"cix":2,"bm":0,"ix":4,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,-0.27],[0,0],[0.03,-0.28],[0.04,-0.24],[0.12,-0.41],[0.09,-0.23],[0.13,-0.26],[4.31,0],[0,5.89],[-5.88,0],[-0.91,-0.26],[0,0],[0,0],[-0.31,-4.24]],"o":[[0,0],[0,0.28],[-0.01,0.23],[-0.07,0.43],[-0.07,0.24],[-0.1,0.27],[-1.67,3.68],[-5.88,0],[0,-5.88],[0.99,0],[0,0],[0,0],[3.84,1.4],[0.02,0.26]],"v":[[10.66,-0.005],[10.66,0.095],[10.62,0.925],[10.54,1.635],[10.26,2.905],[10.03,3.615],[9.7,4.415],[0,10.655],[-10.66,-0.005],[0,-10.655],[2.86,-10.265],[3.66,-10.005],[3.67,-10.005],[10.63,-0.805]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.677999997606,0.713999968884,0.74900004069,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[17.803,10.905],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 6","np":4,"cix":2,"bm":0,"ix":5,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,-0.27],[0,0],[0.03,-0.28],[0.04,-0.24],[0.12,-0.42],[0.08,-0.23],[0,0],[0,0],[0,0],[0,0],[0.19,-0.11],[0.09,-0.04],[0.05,-0.02],[0,0],[0.04,-0.02],[1.01,1.07],[0.07,0.75],[-0.83,0.77],[-0.1,0.08],[-0.08,0.04],[-0.03,0.02],[0,0],[-0.03,0.02],[-0.19,0.06],[0,0],[0,0],[-0.23,-0.1],[0,0],[-0.33,-4]],"o":[[0,0],[0,0.28],[-0.01,0.23],[-0.06,0.43],[-0.06,0.24],[0,0],[0,0],[0,0],[0,0],[-0.16,0.16],[-0.09,0.06],[-0.06,0.03],[0,0],[-0.05,0.02],[-1.3,0.5],[-0.55,-0.59],[-0.09,-1.04],[0.09,-0.08],[0.08,-0.06],[0.04,-0.02],[0,0],[0.04,-0.01],[0.17,-0.1],[0,0],[0,0],[0.24,0.08],[0,0],[3.73,1.51],[0.02,0.26]],"v":[[7.077,2.684],[7.077,2.784],[7.037,3.613],[6.957,4.324],[6.687,5.594],[6.467,6.303],[6.447,6.303],[4.557,6.344],[-1.183,6.454],[-1.193,6.454],[-1.723,6.863],[-2.003,7.023],[-2.163,7.104],[-2.173,7.104],[-2.303,7.163],[-6.133,6.284],[-7.063,4.214],[-5.953,1.333],[-5.673,1.094],[-5.433,0.934],[-5.333,0.874],[-5.333,0.863],[-5.233,0.814],[-4.683,0.574],[-1.933,-4.417],[-0.293,-7.396],[0.417,-7.137],[0.437,-7.126],[7.047,1.883]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.33300000359,0.352999997606,0.368999974868,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[21.387,8.217],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 7","np":4,"cix":2,"bm":0,"ix":6,"mn":"ADBE Vector Group","hd":false},{"ty":"tr","p":{"a":0,"k":[18.148,11.315],"ix":2},"a":{"a":0,"k":[18.148,11.315],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.167],"y":[0.167]},"t":0,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":105,"s":[-12]},{"i":{"x":[0.833],"y":[0.833]},"o":{"x":[0.333],"y":[0]},"t":255,"s":[10]},{"t":359,"s":[0]}],"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 9","np":6,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[2.042,-0.685],[0,0],[1.556,4.622],[0,0.001],[0,0],[-4.622,1.556],[-0.001,0],[0,0]],"o":[[0.611,2.065],[0,0],[-4.621,1.556],[-0.001,0],[0,0],[-1.556,-4.621],[0.001,0],[0,0],[0,0]],"v":[[14.519,18.051],[11.954,22.98],[7.843,24.357],[-3.343,18.807],[-3.344,18.805],[-13.574,-11.579],[-8.024,-22.766],[-8.022,-22.767],[1.511,-25.913]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.426999978458,0.477999997606,0.528999956916,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[15.38,44.862],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 8","np":4,"cix":2,"bm":0,"ix":3,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":8,"ty":4,"nm":"arkaBacak Outlines","parent":7,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.538],"y":[0]},"t":0,"s":[25]},{"i":{"x":[0.907],"y":[1]},"o":{"x":[0.189],"y":[0]},"t":145,"s":[-10]},{"t":359,"s":[25]}],"ix":10},"p":{"a":0,"k":[43.819,54.408,0],"ix":2},"a":{"a":0,"k":[13.351,8.202,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0.113,0.672],[0,0],[0.298,-0.051],[0,0],[-0.05,-0.296],[0,0],[0,0],[-0.781,0.133],[0,0],[0,0],[0,0],[0.109,0.654],[0,0.003],[0.456,0.119],[0,0]],"o":[[0,0],[-0.052,-0.297],[0,0],[-0.297,0.051],[0,0],[0,0],[0.134,0.781],[0,0],[0,0],[0,0],[0.653,-0.109],[0,-0.002],[-0.079,-0.466],[0,0],[-0.66,-0.176]],"v":[[0.641,-1.74],[0.386,-3.236],[-0.246,-3.682],[-4.423,-2.969],[-4.87,-2.34],[-4.87,-2.34],[-4.056,2.426],[-2.399,3.6],[0.737,3.064],[1.437,2.945],[3.826,2.536],[4.811,1.156],[4.81,1.148],[3.93,0.186],[1.909,-0.348]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[31.032,26.921],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false},{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-4.018,-1.209],[-3.45,-3.781],[0,0],[1.56,2.418],[9.477,-3.43]],"o":[[4.018,1.209],[0,0],[-0.801,-4.157],[-1.559,-2.418],[-9.477,3.431]],"v":[[-5.965,3.317],[10.578,11.926],[15.442,11.168],[9.442,-3.487],[-5.965,-8.496]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.517999985639,0.552999997606,0.592000026329,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[15.692,12.176],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 2","np":4,"cix":2,"bm":0,"ix":2,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":9,"ty":4,"nm":"arkaBilek Outlines","parent":12,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[33.029,4.707,0],"ix":2},"a":{"a":0,"k":[1.455,2.644,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[-0.301,-0.016],[-0.841,0.158],[0.723,-0.976],[-0.269,-0.445],[0.772,0.571],[0.445,0.064],[0.878,-0.223],[0.053,0.301],[-0.003,0.045]],"o":[[0.02,-0.301],[1.251,0.253],[1.868,-0.351],[-0.631,0.854],[0.32,0.533],[-0.391,-0.291],[-0.445,-0.065],[-0.301,0.054],[-0.008,-0.045],[0,0]],"v":[[-3.486,-2.13],[-2.909,-2.644],[0.996,-2.449],[2.764,-0.105],[2.234,1.458],[1.076,2.229],[-0.152,1.598],[-2.562,1.891],[-3.202,1.443],[-3.211,1.308]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[3.736,3.05],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":10,"ty":4,"nm":"arkPrmkUst Outlines","parent":9,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[23.823]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":81,"s":[0]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":175,"s":[23.823]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":256,"s":[-7.891]},{"t":359,"s":[23.823]}],"ix":10},"p":{"a":0,"k":[5.178,2.116,0],"ix":2},"a":{"a":0,"k":[2.062,3.406,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-1.157,0.548],[-0.385,-0.384],[0.815,-1.119],[0.759,0.478]],"o":[[1.156,-0.548],[0.372,0.371],[-0.357,0.492],[-0.798,-0.502]],"v":[[-1.654,-0.614],[2.439,-1.991],[0.257,1.7],[-1.794,1.896]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[3.061,2.625],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":11,"ty":4,"nm":"arkPrmkAlt Outlines","parent":9,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[-18.606]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":84,"s":[23.621]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":175,"s":[-18.606]},{"i":{"x":[0.833],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":258,"s":[23.621]},{"t":359,"s":[-18.606]}],"ix":10},"p":{"a":0,"k":[5.317,4.798,0],"ix":2},"a":{"a":0,"k":[1.033,0.935,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-0.19,-0.363],[0.531,-0.171],[0.294,0.213],[-0.424,0.209]],"o":[[0.544,1.039],[-0.4,0.13],[-0.334,-0.243],[0.288,-0.141]],"v":[[0.084,-0.82],[0.823,1.087],[-1.02,-0.008],[-0.852,-1.076]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.349000010771,0.380000005984,0.404000016755,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[1.603,1.467],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":12,"ty":4,"nm":"arkaKol Outlines","parent":7,"sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":0,"s":[-6]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":90,"s":[10]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":180,"s":[-6]},{"i":{"x":[0.667],"y":[1]},"o":{"x":[0.333],"y":[0]},"t":270,"s":[12]},{"t":359,"s":[-6]}],"ix":10},"p":{"a":0,"k":[19.477,29.316,0],"ix":2},"a":{"a":0,"k":[6.726,6.532,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[-2.298,0],[0,0],[0,0],[2.51,-0.399],[-0.624,3.866]],"o":[[2.298,0],[0,0],[0,0],[-3.909,0.623],[0.755,-4.678]],"v":[[-8.01,-6.532],[15.428,-4.138],[16.477,-0.159],[-8.824,5.909],[-15.853,-0.981]],"c":true},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"fl","c":{"a":0,"k":[0.517999985639,0.552999997606,0.592000026329,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[16.726,6.782],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Group 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":13,"ty":0,"nm":"yildizlar","refId":"comp_0","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[115.5,47.5,0],"ix":2},"a":{"a":0,"k":[115.5,47.5,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"w":231,"h":95,"ip":0,"op":360,"st":0,"bm":0},{"ddd":0,"ind":14,"ty":0,"nm":"yildiz","refId":"comp_1","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[115.5,47.5,0],"ix":2},"a":{"a":0,"k":[115.5,47.5,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"w":231,"h":95,"ip":0,"op":360,"st":0,"bm":0}],"markers":[]}');

/***/ })

};
;

// load runtime
var __webpack_require__ = require("../webpack-runtime.js");
__webpack_require__.C(exports);
var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
var __webpack_exports__ = __webpack_require__.X(0, [664,597,675,687,865,338,619], () => (__webpack_exec__(7025)));
module.exports = __webpack_exports__;

})();