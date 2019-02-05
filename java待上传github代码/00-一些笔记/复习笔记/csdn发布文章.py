import requests,time,urllib
from requests_toolbelt  import MultipartEncoder

#富文本编辑器  https://mp.csdn.net/postedit?not_checkout=1
ua='Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Mobile Safari/537.36'
url0='https://mp.csdn.net/mdeditor#'
url='https://passport.csdn.net/v1/register/pc/sendVerifyCode'
urlpost='https://mp.csdn.net/postedit/saveArticle?isPub=1'
h={'user-agent':ua,'Referer': 'https://passport.csdn.net/signwapn',
   'X-Requested-With': 'XMLHttpRequest','Accept-Language': 'zh-CN,zh;q=0.9',
   'Accept-Encoding': 'gzip, deflate, br','Origin':'https://passport.csdn.net',
   'Accept': 'application/json, text/plain, */*',
   'Content-Type': 'application/json;charset=UTF-8'}
cookies={
'uuid_tt_dd':'10_10206497820-1548036964904-841824',
'dc_session_id':'10_1548036964904.153434',
'firstDie':'1',
'SESSION':'d708f9e3-2b08-4890-95e0-462897cfd728',
'dc_tos':'pm8lse',
'tipShow':'true',
'Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac':'5744*1*weixin_43292547',
'Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac':'1549004589,1549005506,1549006203,1549007439',
'Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac':'1549007439',
'c-login-auto':'6',
'UserName':'weixin_43292547',
'UserInfo':'51f3aa53076349f9a20218a3b859966b',
'UserToken':'51f3aa53076349f9a20218a3b859966b',
'UserNick':'%E6%B1%9F%E6%8C%91',
'AU':'86D',
'UN':'weixin_43292547',
'BT':'1549007658804',}

#titl：标题，cont：内容，tag2：标签
#categories：分类/fl1,fenlei1分类1
data1={'titl':'ttt','typ':'1','private':'false',
      'cont':'asd123阿斯顿赛季方法及即将前往i耳机即将发生',
      'level':'0','categories':'','chnl':'16','artid':'0','stat':'publish'}
h1={'Referer': 'https://mp.csdn.net/postedit?not_checkout=1',
    'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}
s=requests.session()
s.cookies.update(cookies)
s.headers.update(h)
#p0=s.get(url0)
#print(p0.text,'\n')
s.headers.update(h1)
p1=s.post(urlpost,data=data1)
print(p1.text)

#urllib.parse.unquote('%E5%A4%A7%E7%86%8A%E7%8C%AB')
#'\u6587\u7ae0\u6807\u9898\u4e0d\u80fd\u4e3a\u7a7a\u3002':'文章标题不能为空。'
#url='http://localhost:8080/ajaxdemo/'
#s=requests.session()
#d={'JSESSIONID':'94B65739FD5DCA3E2D826D73BAF7CC13'}
#s.cookies.update(d)
#r=s.get(url)
