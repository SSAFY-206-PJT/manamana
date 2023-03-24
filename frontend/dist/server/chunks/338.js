"use strict";
exports.id = 338;
exports.ids = [338];
exports.modules = {

/***/ 1338:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {


// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "Z": () => (/* binding */ WebtoonItem)
});

// EXTERNAL MODULE: external "react"
var external_react_ = __webpack_require__(6689);
var external_react_default = /*#__PURE__*/__webpack_require__.n(external_react_);
;// CONCATENATED MODULE: ./components/common/WebtoonBreakLabel.tsx

var __jsx = (external_react_default()).createElement;
function WebtoonBreakLabel() {
  return __jsx("div", {
    className: "mr-1 inline-block flex h-4 w-6 flex-row items-center justify-center rounded-md bg-SecondaryLight"
  }, __jsx("p", {
    className: "text-[10px] font-bold text-FontPrimaryDark"
  }, "\uD734\uC7AC"));
}
;// CONCATENATED MODULE: ./components/common/WebtoonCompleteLabel.tsx

var WebtoonCompleteLabel_jsx = (external_react_default()).createElement;
function WebtoonCompleteLabel() {
  return WebtoonCompleteLabel_jsx("div", {
    className: "mr-1 inline-block h-4 w-6 rounded-md bg-PrimaryLight"
  }, WebtoonCompleteLabel_jsx("span", {
    className: "flex h-full items-center justify-center text-[10px] font-bold text-FontPrimaryDark"
  }, "\uC644\uACB0"));
}
;// CONCATENATED MODULE: ./components/common/WebtoonItem.tsx

var WebtoonItem_jsx = (external_react_default()).createElement;


function WebtoonItem(props) {
  var imageUrl = props.imageUrl;
  var webtoonName = props.webtoonName;
  var status = props.status;

  var onWebtoonClick = function onWebtoonClick() {};

  return WebtoonItem_jsx("div", {
    className: "inline-block",
    onClick: onWebtoonClick
  }, WebtoonItem_jsx("div", {
    className: "mr-2 inline-block flex flex-col"
  }, WebtoonItem_jsx("img", {
    src: imageUrl,
    alt: "imageURL",
    className: "h-30 mt-2 w-24"
  }), WebtoonItem_jsx("div", {
    className: "mt-1 flex h-4 flex-row items-center text-sm"
  }, status === '휴재중' ? WebtoonItem_jsx(WebtoonBreakLabel, null) : WebtoonItem_jsx((external_react_default()).Fragment, null), status === '완결' ? WebtoonItem_jsx(WebtoonCompleteLabel, null) : WebtoonItem_jsx((external_react_default()).Fragment, null), WebtoonItem_jsx("div", {
    className: "flex items-center justify-center text-[16px] text-semibold"
  }, webtoonName))));
}

/***/ })

};
;