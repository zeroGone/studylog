const editorContainer = document.querySelector('.editor-container');

const viewer = toastui.Editor.factory({
    el: editorContainer,
    viewer: true,
    height: 'auto',
    initialValue: editorContainer.dataset.contents
});