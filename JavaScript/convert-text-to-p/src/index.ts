class ConvertTextToParagraph {
  public handleClick(_event: Event) {
    const inputTextArea = document.querySelector('textarea#input-textarea') as HTMLTextAreaElement;
    const convertedText = this._convertToParagraph(inputTextArea.value);
    this._setOutputText(convertedText);
  }

  private _convertToParagraph(inputStr: string): string {
    const arr1: string[] = [];
    for (const line of inputStr.split('\n')) {
      // Check if string is not null and string is not whitespace only
      // Ref: https://stackoverflow.com/a/10262019
      if (line && line.replace(/\s/g, '').length > 0) {
        arr1.push(`<p>${line}</p>`);
      }
    }

    return arr1.join('\n');
  }

  private _setOutputText(inputStr: string) {
    const outputTextArea = document.querySelector('textarea#output-text-area') as HTMLTextAreaElement;
    outputTextArea.value = inputStr;
  }
}

const inputButton = document.querySelector('button#input-button');
const convertTextToParagraph = new ConvertTextToParagraph();

function handleInputButtonClick(event: Event) {
  convertTextToParagraph.handleClick(event);
}
inputButton?.addEventListener('click', handleInputButtonClick);

const outputTextArea = document.querySelector('textarea#output-text-area') as HTMLTextAreaElement;
function handleOutputTextAreaFocus(_event: Event) {
  outputTextArea.select();
}
outputTextArea?.addEventListener('focus', handleOutputTextAreaFocus);
