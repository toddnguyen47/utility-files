window.onload = () => {
  const codeElements = document.querySelectorAll('div.content-cell code');

  for (let i = 0; i < codeElements.length; i++) {
    const codeElement = codeElements[i];

    // Reference: https://stackoverflow.com/a/35299417
    codeElement.addEventListener('click', (_event) => {
      if (document.body.createTextRange) {
        range = document.body.createTextRange();
        range.moveToElementText(codeElement);
        range.select();
      } else if (window.getSelection) {
        selection = window.getSelection();
        range = document.createRange();
        range.selectNodeContents(codeElement);
        selection.removeAllRanges();
        selection.addRange(range);
      } else {
        console.warn('Select not supported!');
      }
    });
  }
};
