module.exports = function isFresh(etag, req) {
  // defaults
  var etagMatches = true;
  var notModified = true;

  // fields
  var modifiedSince = req.getHeader("if-modified-since");
  var noneMatch = req.getHeader("if-none-match");
  var noneMatchTokens = null;

  // unconditional request
  if (modifiedSince == null && noneMatch == null) {
    return false;
  }

  // parse if-none-match
  if (noneMatch != null) {
    noneMatchTokens = noneMatch.split(" *, *");
  }

  // if-none-match
  if (noneMatchTokens != null) {
    etagMatches = false;
    for (var i = 0; i < noneMatchTokens.length; i++) {
      if (noneMatchTokens[i] == etag || "*" == noneMatchTokens[0]) {
        etagMatches = true;
        break;
      }
    }
  }

  // if-modified-since
  if (modifiedSince != null) {
    try {
      var modifiedSinceDate = new Date(modifiedSince);
      notModified = Date.now() <= modifiedSinceDate;
    } catch (e) {
      notModified = false;
    }
  }

  return etagMatches && notModified;
};
