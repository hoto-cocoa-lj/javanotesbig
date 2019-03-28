#!/user/bin/env python
#-*-coding:utf-8-*-
#time:2019/3/26

import requests,time,random
from 读取笔记在csdn发布博客 import *

#使用富文本编辑器可以用代码上传文章，地址是  https://mp.csdn.net/postedit?not_checkout=1
ua='Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36'
urlpost='https://mp.csdn.net/postedit/saveArticle?isPub=1'
h={'User-Agent':ua,
   'Accept':'*/*',
   'Connection': 'keep-alive',
   'X-Requested-With': 'XMLHttpRequest',
   'Accept-Language': 'zh-CN,zh;q=0.9',
   'Accept-Encoding': 'gzip, deflate, br',
   'Origin':'https://mp.csdn.net',
   'Accept-Language':'zh-CN,zh;q=0.9',
   'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
}

h1={'Referer': 'https://mp.csdn.net/postedit?not_checkout=1',
    'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}




# s = 'uuid_tt_dd=10_2364702860-1552309400163-440498; smidV2=201903181236232461e469d5bd5df355fb4e17a9c968d700c43506fe0ee16c0; _ga=GA1.2.1312090355.1552908109; UserName=weixin_43292547; UserInfo=16bd2998a103497ca55b986d9cd1ef5d; UserToken=16bd2998a103497ca55b986d9cd1ef5d; UserNick=%E6%B1%9F%E6%8C%91; AU=86D; UN=weixin_43292547; BT=1552908401924; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_2364702860-1552309400163-440498!1788*1*PC_VC!5744*1*weixin_43292547; dc_session_id=10_1553311299019.833443; firstDie=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1553599379,1553599380,1553599535,1553600694; TINGYUN_DATA=%7B%22id%22%3A%22-sf2Cni530g%23HL5wvli0FZI%22%2C%22n%22%3A%22WebAction%2FCI%2FpostList%252Flist%22%2C%22tid%22%3A%226931e62b2413c2%22%2C%22q%22%3A0%2C%22a%22%3A76%7D; dc_tos=poz2dh; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1553601222'
def getCookieDict(cookieStr):
    a = cookieStr.replace(" ", '').split(";")
    d = {}
    for i in range(len(a)):
        b = a[i].split("=")
        d[b[0]] = b[1]
    return d

def getContext(filename):
    with open(filename, 'r', encoding='utf-8') as file:
        print(os.getcwd())
        s = file.readlines()
        for i in range(len(s)):
            s[i] = formatStr(s[i])

        s='<br/>'.join(s)
        print(s)
        return s

#有很多html标签会被删除所以去掉一边括号,把tab换成4个空格
def formatStr(str):
    return str.replace("\t", "&nbsp;" * 4)\
        .replace('>', '#r')\
        .replace('<','#l');

def publishOne(session,filename,data1):
    s=getContext(filename)
    h2={}
    #h2['Content-Length'] = "{}".format(len(s.encode('utf-8')))
    #print("h2={}".format(h2))
    #session.headers.update(h2)
    data1['cont']= s
    p1 = session.post(urlpost, data=data1,verify=False)
    res=p1.json()
    print(p1.json())
    return '成功' in res['content']

if __name__ == '__main__':
    #cookies={'uuid_tt_dd': '10_2364702860-1552309400163-440498', 'smidV2': '201903181236232461e469d5bd5df355fb4e17a9c968d700c43506fe0ee16c0', '_ga': 'GA1.2.1312090355.1552908109', 'UserName': 'weixin_43292547', 'UserInfo': '16bd2998a103497ca55b986d9cd1ef5d', 'UserToken': '16bd2998a103497ca55b986d9cd1ef5d', 'UserNick': '%E6%B1%9F%E6%8C%91', 'AU': '86D', 'UN': 'weixin_43292547', 'BT': '1552908401924', 'Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac': '6525*1*10_2364702860-1552309400163-440498!1788*1*PC_VC!5744*1*weixin_43292547', 'dc_session_id': '10_1553311299019.833443', 'firstDie': '1', 'Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac': '1553599379,1553599380,1553599535,1553600694', 'TINGYUN_DATA': '%7B%22id%22%3A%22-sf2Cni530g%23HL5wvli0FZI%22%2C%22n%22%3A%22WebAction%2FCI%2FpostList%252Flist%22%2C%22tid%22%3A%226931e62b2413c2%22%2C%22q%22%3A0%2C%22a%22%3A76%7D', 'dc_tos': 'poz2dh', 'Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac': '1553601222'}
    cookies={'ARK_ID': 'JS55d2d1634edcefcb23e40ca114b5637955d2', 'smidV2': '20180926144827c9c539353dcab7518c244a500e42198800cc47023a3571420', '__yadk_uid': 'bSEwuryfyqaxQXIZA0l13cVQeyQwRxCx', 'Hm_lvt_ba7c84ce230944c13900faeba642b2b4': '1540455070,1540455391,1540456158,1540457343', 'uuid_tt_dd': '10_28867322940-1540699535834-946464', '_ga': 'GA1.2.464066008.1541653399', 'UM_distinctid': '167308f6f9c0-02288610b6fae7-454c092b-1fa400-167308f6f9d22', 'bdshare_firstime': '1548069455411', 'dc_session_id': '10_1549009365112.326806', 'Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac': '1552377640,1552377641,1552465733,1553605582', 'SESSION': 'b1737bd8-943c-4b75-8451-4c2b0568f88e', 'c_adb': '1', 'UserName': 'pyg112358', 'UserInfo': 'cb19fbe8d50a4344b2c604addcfa72e3', 'UserToken': 'cb19fbe8d50a4344b2c604addcfa72e3', 'UserNick': 'pyg112358', 'AU': '3BF', 'UN': 'pyg112358', 'BT': '1553606263431', 'Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac': '1788*1*PC_VC!5744*1*pyg112358!6525*1*10_28867322940-1540699535834-946464', 'dc_tos': 'poz67w', 'Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac': '1553606205'}

    # titl：标题，cont：内容，
    # tag2='sql,dao'表示属于sql和dao 文章标签
    # typ=1表示原创，chnl表示博客分类(16表示编程语言)
    # categories='java,web'表示属于web和java两个 个人分类
    #发现几个bug，
    #1、有->的话，前面的文本在上传服务器后会被删除
    #2、有body的话，body外的部分会被服务器删除
    #html,div,<!-等也会删除,所以把<>全部替换成#

    data1 = {'titl': 'renmen1',
             'typ': '1',
             'private': 'false',
             'level': '0',
             'categories': 'java相关',
             'tag2':'17-Lucene&solr入门&进阶',
             'chnl': '16',
             'artid': '0',
             'stat': 'publish'}
    session = requests.session()
    session.cookies.update(cookies)
    session.headers.update(h)
    session.headers.update(h1)
    path2='00-一些笔记';
    filename=path2+'_csdnBlogDict.bin'
    dic=readCsdnBlogDict(filename)
    count=0
    try:
        for i in dic.keys():
            if 'txt' in i and (not dic[i]):
                name=i.split("\\")[-1].split(".")[0]
                data1['titl']=name
                data1['tag2'] = path2
                res=publishOne(session, i, data1)
                if res:
                    count+=1
                    print("发布第{}篇成功，修改dict".format(count))
                    dic[i]=True
                    if count >= 10:
                        break
                    time.sleep(30+random.randrange(1,5))
    finally:
        writeCsdnBlogDict(dic, filename)
        print("保存dict到硬盘")
    #publishOne(session,'1.txt',data1)