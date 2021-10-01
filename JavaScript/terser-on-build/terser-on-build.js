/**
 * NOTE: This script is meant to run with `npm run`, hence why `./node_modules` is used.
 * See `package.json` under the `scripts` node
 */

const fs = require('fs');
const util = require('util');
const childProcess = require('child_process');

const exec = util.promisify(childProcess.exec);
const readdir = util.promisify(fs.readdir);

// Ref: https://www.npmjs.com/package/terser
// Comma separated list of compress options. NO SPACES
const _compressOptions = 'defaults=true,arrows=true';
// Comma separated list of mangle options. NO SPACES
const _mangleOptions = '';
const _terserCommand = [
  './node_modules/terser/bin/terser',
  '--format quote_style=1',
  '--ecma 2018',
  `--compress ${_compressOptions}`,
  `--mangle ${_mangleOptions}`,
];
const _dirPath = 'build/default/src';

/**
 * Recursively dive into dirpath.
 *
 * Ref: https://stackoverflow.com/q/7041638/6323360
 * @param {*} dirPath Directory path
 * @param {Function} action Function to take. Will accept func(dirPath: String)
 */
async function dive(dirPath, action) {
  // Assert action is a function
  if (typeof action !== 'function') {
    console.err('action is not a function');
    return 'error';
  }

  await readdir(dirPath, (err1, files) => {
    if (err1) {
      console.error('err on dirPath: ' + dirPath);
      return 'error';
    }

    for (const file of files) {
      // Full path
      const fullPath = dirPath + '/' + file;
      // Get file's stats
      fs.stat(fullPath, (err2, stat2) => {
        if (err2) {
          console.err('err on stat: ' + stat2);
          return 'error';
        } else if (stat2 && stat2.isDirectory()) {
          // If directory
          dive(fullPath, action);
        } else {
          // If file
          action(fullPath);
        }
        return 'success';
      });
    }
    return 'success';
  });
  return 'success';
}

/**
 * @param {String} file Fullpath to file
 */
async function actionPerFile(file) {
  if (file.endsWith('js')) {
    const commandList = _terserCommand.concat(['--output', file, ' -- ', file]);
    const command = commandList.join(' ');
    console.info('Command: ' + command);

    try {
      await exec(command);
      return 'success';
    } catch (e) {
      console.error(e);
      return 'error';
    }
  }
  return 'success';
}

async function _main() {
  console.info('\n|-- Starting terser --|');
  await dive(_dirPath, actionPerFile);
}

_main();
