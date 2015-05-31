'use strict';

exports.__esModule = true;
exports['default'] = fillKeys;

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }

var _mergeDescriptors = require('merge-descriptors');

var _mergeDescriptors2 = _interopRequireDefault(_mergeDescriptors);

var _isObject = require('is-object');

var _isObject2 = _interopRequireDefault(_isObject);

'use strict';

var hasOwnProperty = Object.prototype.hasOwnProperty;

function fill(destination, source, merge) {
  if (destination && (_isObject2['default'](source) || isFunction(source))) {
    merge(destination, source, false);
    if (isFunction(destination) && isFunction(source)) {
      merge(destination.prototype, source.prototype, false);
    }
  }
  return destination;
}

function fillKeys(destination, source) {
  return fill(destination, source, _mergeDescriptors2['default']);
}

fillKeys.es3 = function fillKeysEs3(destination, source) {
  return fill(destination, source, es3Merge);
};

function es3Merge(destination, source) {
  for (var key in source) {
    if (!hasOwnProperty.call(destination, key)) {
      destination[key] = source[key];
    }
  }
  return destination;
}

function isFunction(value) {
  return typeof value === 'function';
}
module.exports = exports['default'];