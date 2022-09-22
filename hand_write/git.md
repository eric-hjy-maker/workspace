# 主要

##基础篇
### 1. git commit
git 原理？
git 提交记录保存的是所有文件的快照，就像复制粘贴一样
但是不会复制整个目录，只是把差异提交
### 2. git branch
git 分支是什么？
git 分支只是简单的指向某个提交记录的指针
<br/>
`git branch newImage` 创建分支<br/>
`git checkout newImage` 切换分支<br/>
`git commit -m "add newImage"` 提交
### 3. git merge
git merge 我要吧这两个父节点都包含进来<br/>
当前在master分支上:<br/>
`git merge newImage` 合并分支<br/>
还在master分支上
### 4. git rebase
含义：取出一系列的提交记录，复制它们，然后在另外一个地方逐个的放下去
当前在bugFix分支上:<br/>
`git rebase master` 将当前分支上的提交记录复制到master分支上<br/>
还在bugFix分支上
##高级篇
### 1. 分离 HEAD
HEAD 指向正在其基础上工作的提交记录<br/>
`git checkout HEAD^` 将HEAD指向上一个提交记录<br/>
`git checkout commitID` 将HEAD指向某个提交记录<br/>
### 2. 相对引用(^)
`git checkout master^` 将HEAD指向上一个提交记录<br/>
### 3. 相对引用2(~)
`git checkout master~3` 将HEAD指向上三个提交记录<br/>
`git branch -f master master~3` 将master分支强制移动
### 4. 撤销变更
`git reset HEAD~1` 撤销记录像没有发生过一样<br/>
`git revert HEAD` 增加撤销记录
##移动提交记录
### 1. git cherry-pick
`git cherry-pick commitID1 commitID2` 复制提交到当前分支<br/>
### 2. 交互式 rebase
如果不清楚commitID, 就可以使用交互式rebase<br/>
`git rebase -i HEAD~3` 交互式rebase<br/>
##杂项
### 1. 只取一个提交记录
`git cherry-pick commitID`
### 2. 提交的技巧 #1 修改历史提交记录
````
// 把历史提交调整到当前位置
git rebase -i HEAD~3
// 修改内容后
git commit --amend
// 把提交调整到原来的位置上
git rebase -i HEAD~3
````
### 3. 提交的技巧 #2 git commit -amend
### 4. git tag
`git tag v1.0 commitID` 给某个提交打标签<br/>
### 5. git describe
`git describe commitID` 查看某个提交距离最近的标签<br/>
## 高级话题
### 1. 多次 rebase
`git rebase branch1 branch2` branch1作为branch2的父节点<br/>
### 2. 两个父节点
````
背景：当前提交有两个父节点
// 默认选择第一个父节点
git checkout HEAD^
// 选择第二个父节点
git checkout HEAD^2
// 还支持链式操作 (第二个分支，向上第二个提交记录)
git checkout HEAD^2~2
````
### 3. 纠缠不清的分支

# 远程
## Push & Pull -- Git 远程仓库
### 1. git clone
### 2. 远程分支
本地的远程分支不能提交代码，如果提交代码会进入HEAD分离状态，o/master不会跟着HEAD走
### 3. git fetch
从远程拉取所有提交记录到本地的远程分支<br/>
### 4. git pull
### 5. 模拟团队合作
`git fakeTeamwork master 3` 模拟给远程分支提交三个记录
### 6. git push
### 7. 偏离的提交历史
###### 基于历史代码开发，别人修改了代码，提交时就会有问题
最简单的方式 rebase
````
git fetch o/master
git rebase o/master
git push
````
还可以使用merge
````
git fetch o/master
git merge o/master
git push
````
简化命令
`git pull --rebase; git push` or `git pull; git push`

## 关于 origin 和它的周边 -- Git 远程仓库高级操作
### 1. 推送主分支
````
git fetch
// 远程分支也可以rebase
git rebase o/master side1 
````
### 2. 合并远程仓库
````
git pull
// 把side的改动合并到当前分支
git merge side1
````
### 3. 远程追踪
###### pull 操作时，默认下载到 o/master， 合并到master分支上
###### push 操作时，默认从master分支推送到 o/master
###### 默认有远程追踪，通过 remote tracking 属性决定
````
// 远程追踪方式1 (设置其他分支的远程追踪)
git checkout -b side1 o/master
// 远程追踪方式2 (设置当前分支的远程追踪)
git branch -u o/master
````
### 4. git push 的参数
````
// 在别的分支时，就可以推送master分支
git push origin master
````
### 5. git push 的参数2
````
// 在别的分支时，推送side分支到o/master
git push origin side:master
````
### 6. git fetch 的参数
````
// 在别的分支时，拉取o/master分支到master分支
git fetch origin master
// 在别的分支时，拉取o/master分支到side分支
git fetch origin master:side
````
### 7. 没有 source 的 source
````
// 删除远程分支
git push origin :side
// 创建本地分支
git fetch origin :bugFix
````
### 8. git pull的参数
````
// 相当于git fetch origin master; git merge o/master
// 就是说从远程拉取master分支，合并到当前分支
git pull origin master
// 从远程拉取master分支，合并到side分支
git pull origin master:side
````
### 9. git 回退版本
````
git reset --hard HEAD^
git push --force
````