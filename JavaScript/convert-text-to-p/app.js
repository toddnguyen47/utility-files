const express = require('express');
const path = require('path');

const app = express();
const port = 3000;

const srcPath = path.join(__dirname, 'src');
app.use('/', express.static(srcPath));

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
