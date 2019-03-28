#!/user/bin/env python
#-*-coding:utf-8-*-
#time:2019/3/26
import os,pickle

#只上传txt文件，从java待上传github代码下一级walk
#遍历给定的path，生成并返回dict，默认value是false
def generateCsdnBlogDict(path):
    a=os.walk(path)
    csdnBlogDict={}
    for root, dirs, files in a:
        for name in files:
            namelist=os.path.splitext(name)
            suffixs=('.xml','.java','.txt')
            if len(namelist)>0 and namelist[-1] in suffixs:
                csdnBlogDict[os.path.join(root, name)]=False
    return csdnBlogDict

#把dict保存到filename，这里我全用相对路径，所以filename是文件名
def writeCsdnBlogDict(dic,filename):
    with open(filename,'wb') as myfile:
        pickle.dump(dic,myfile)

#返回filename的dict
def readCsdnBlogDict(filename):
    with open(filename, 'rb') as myfile:
        myfile.seek(0)
        return pickle.load(myfile)



#读取dict，生成newdict，把dict的信息覆盖到newdict再保存newdict
if __name__ == '__main__':
    path=r'E:\GitHub\javanotesbig\java待上传github代码\\'
    sonPath='00-一些笔记'
    path=path+sonPath
    filename=sonPath+'_csdnBlogDict.bin'
    csdnBlogDict={}
    if os.path.exists(filename):
        print("file exists:{}".format(filename))
        csdnBlogDict = readCsdnBlogDict(filename)
    newDict=generateCsdnBlogDict(path)

    for i in csdnBlogDict.keys():
        newDict[i]=csdnBlogDict[i]

    writeCsdnBlogDict(newDict,filename)
    #countTxt,countXml,countJava=0,0,0
    countList=[0,0,0]
    uncountList = [0, 0, 0]
    suffixsList = ['.xml', '.java', '.txt']
    for i in newDict.keys():
        namelist = os.path.splitext(i)
        index=suffixsList.index(namelist[-1])
        if not newDict[i]:
            countList[index]+=1
            print('未完成:{}'.format(i))
        else:
            uncountList[index] += 1
    print("一共{}个文件".format(len(newDict.keys())))
    for i in range(len(countList)):
        a,b,c=suffixsList[i], countList[i],uncountList[i]
        print('{}没完成{}个完成{}个'.format(a,b,c))
