# DMASCM (DesignMyApp Style Coding Manager)
# How to work with Git repository and DesignMyApp

# 1: Prerequisites

* The project must be cloned into your working Directory.
* A [GitHub](https://github.com/) account.
* The right to work on the repository.
* Knowledge about Git.

# 2: General Rules

* **NEVER** work on the master branch *(see exceptions for details)*.
* **NEVER** commit or push modifications in branch *master* without at least one review
* **NEVER** use `git pull` to update, use `git fetch` instead
* **ALWAYS** work in a separate branch from the master
* **ALWAYS** use CamelCase to name your branches
* **ALWAYS** follow the previous rules

# 3: Conventions

* **Naming**

	If you want to add a feature to the project, your branch should be named like this : 
	>`feature/nameOfTheFeature`

	If you work on a Bug reported in Mantis (with an id), your branch should be named like this : 
	>`bugfix/_idDuBeug_`

	If you work on an Hotfix (not reported in Mantis, your should named your branch like this : 
	>`hotfix/_VeryShortDescription_`

	*Add the conventions for naming tagging and snapshot*


* **Exceptions *(very rare)***

	If you spot a bug and you are sure you can correct it in a single commit, you *can* work directly on branch master but you have to be sure of yourself.


# 4: Work flow in DMA

# Step 1 : Create your branch

First, you have to create the branch where you are going to work *(remember to the Naming Conventions)*  
>`git checkout -b myBranch`
	
> If the branch *myBranch*  already exists, your HEAD will be placed on the existing branch. If *myBranch* does not exist, the branch will be created and your HEAD will be placed in *myBranch*.

You can check the branch where you are in two ways :

* by using command `git status`. The first line should be like `# On branch myBranch`.
* by using command `git branch`. It will list all your local branches. the branch where you are is preceding by a `*`

# Step 2 : Working

Now you can make modifications in the code, test it, add it and commit it to your branch. Here is a typical step-by-step workflow : 

`git status`
> It will list all the modifications you do since your last commit. 

>If your local repo is well-configured, the unstaged changes should appears in red in your console.


`git add .`
> This will move all your modifications (including created files) from the working directory to the stagging area.
  
> If you do not want to move the created files in the stagging area, you can use `git add -u`  
> If your local repo is well-configured, the stagged changes should appears in green in your console.

> **NEVER** use the commands `git add --all` or `git add -A` *(this is very bad)*

`git commit -m "insert here the commit message"`
> This will move all the files from the stagging area to the repository.

> Remember that a developer is a lazy man... Try to be clear and concise in your commit message

---
## **What should I do if someone else need my work or if I need the work of another dev'?**

###  Case 1 : Someone else need my work

If another developper need your work **immediatly** to progress but your feature is not complete, or your bugfix is not finished. **DO NOT push on the master your incomplete changes**.

Use this instead :
 
`git push origin myBranch`
> This push your branch in the distant repository and allow other devs to see and use your precious work


### Case 2 : I need the work from another dev

Do not forget to place your HEAD on your branch *myBranch* and use :

`git pull origin veryUsefullOtherDevBranch`
> This will pull all changes pushed in the *veryUsefullOtherDevBranch* into your current branch

> Watch out for conflicts !

---

# Step 3 : Finishing and merging your work

# Prerequisites
Do not forget to validate your work by another competent developer or the project leader or the dev leader 
We assume that your code is functionnal and clean

# Cleaning your branch

### Case 1 : you worked on a feature
Typically, a feature need more than one commit to be devellop. But too many commit can make the *master* branch very messy. 

First, you have to clean your branch with :

`git rebase -i HEAD~X` 
> where X is in general the number of commits in your branch 

Merge minor commits into one bigger by using `squash` keyword
Edit the comment of your commit by using `edit` keyword and 

`git rebase --amend -m "new message for commit"`
> Be sure that all your commit messages have to be like **[Feature:nameOfTheFeature] commit description**

### Case 2 : you worked on a bugfix or a hotfix
In general, only one commit should appears in the *master* branch to correct a bug. But you may need more than one commit in dev to completely correct a bug. If you have several commits that can be merged, merge it by using

`git rebase -i HEAD~X`
> where X is in general the number of commits in your branch  

Do not forget to rename your commit with ` git rebase --amend -m "new message`.

For a hotfix your commit message had to be like **[Hotfix] commit message**

For a bugfix (reported in Mantis with an id, your commit message had to be like **[Bugfix:idDuBeug] commit message**

# Update and rebase

First, you have to update your master to be aware of the modifications done by the other devs. 

Go on the *master* branch

`git checkout master`

and update it

`git fetch`
> if you are scared you can use `git fetch --all` or `git fetch origin master` but logically, the `git fetch` command does the same thing.

Then go back to your branch

`git checkout myBranch`

and put your branch on the top of the tree with

`git rebase origin/master`
> Do not forget to put `origin/master` and not only `master` to avoid branch divergence.

> In this state, you may have some conflict to solve. Use your *merge-tool* to solve them.

> If you do not have conflict here, you are just lucky. 

# Merging
Now, it is time to merge your branch into the master and show all your god-like modifications to the Humankind.

Go back to the *master* branch,

`git checkout master`

and merge your branch into it,

`git merge myBranch`

Finally, push your merging into the distant repository.

`git push`
> you may have to specify `git push origin master` if your configuration does not stipulate it. Default settings make the `git push` acts like `git push origin master`.


# Cleaning the repository

Last step, you need to clean up your local repository. Indeed, you do not need your *feature/bugfix/hotfix* branch anymore.

To clean your local repository, you have to delete the branch you have just merged.

`git branch -D myBranch`
> Remember, it will **only** delete your branch from your local repository.

Then, if you pushed your branch on the master (to share your modifcations with other devs) you have to clean the distant repository by deleting the branch you pushed before.

`git push origin :myBranch`

---

#Additionnal Ressources

[Github](https://github.com/)  
[Git official documentation](http://git-scm.com/documentation)  
[Git Excilys formation](http://excilys.github.io/parlez-vous-git/course/#/)