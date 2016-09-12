# Cards

# Git instructions

## Use Cygwin to navigate through folders

lookup linux directory cheatsheets for command instructions (*cd*, *li*, *mkdir*, etc.)

## Clone this repository to your local machine

git clone https://github.com/brendanfrick2016/Cards.git

## Before you work on a file
#### Check for updates (If you don't do this you'll have to do a merge request which gets messy) 
git pull *from the Cards folder*

## When you're done working on a file
#### Add files 
###### To add all files in Cards (if you change a couple of files)
git add .
###### If you only changed one or two files its easier to add just those
git add file-name

#### Commit changes (this will tell you what files are being inserted/deleted
git commit -m "type a message here or git will get mad"

#### 'Upload' your changes
git push *If you get an error here its likely a merge error and follow next step, or text em*

#### Merge (if there are conflicting copies, either you didn't pull or I made changes since you last did)
git merge -m "type something"
