'use strict'

import mergeDescriptors from 'merge-descriptors'
import isObject from 'is-object'
const hasOwnProperty = Object.prototype.hasOwnProperty

function fill (destination, source, merge) {
  if (destination && (isObject(source) || isFunction(source))) {
    merge(destination, source, false)
    if (isFunction(destination) && isFunction(source)) {
      merge(destination.prototype, source.prototype, false)
    }
  }
  return destination
}

export default function fillKeys (destination, source) {
  return fill(destination, source, mergeDescriptors)
}

fillKeys.es3 = function fillKeysEs3 (destination, source) {
  return fill(destination, source, es3Merge)
}

function es3Merge (destination, source) {
  for (let key in source) {
    if (!hasOwnProperty.call(destination, key)) {
      destination[key] = source[key]
    }
  }
  return destination
}

function isFunction (value) {
  return typeof value === 'function'
}
