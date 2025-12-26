# Git Tips and Tricks

## Squash a merge commit

https://stackoverflow.com/a/69827502

```shell
git checkout -b temp main
git merge --squash feature
git commit
git checkout feature
git reset --hard temp
git branch -d temp

# If using oh-my-zsh's git plugin
# gpsupf='git push --set-upstream origin $(git_current_branch) --force-with-lease --force-if-includes'
```
