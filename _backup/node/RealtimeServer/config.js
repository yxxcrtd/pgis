module.exports={
	//realtimeServer端口
	port: 15498,
	
	/*
	终端重启后，本程序会以这个配置发起Http请求（Get方法），
	请求的实际path是 path + 控制卡序列号
	若host为空，则不请求
	*/
	restartNotification:{
		//ip或域名
		//host: '192.168.8.99',
		//端口
		port: 15497,		
		//路径
		path: '/test?cardId=',
	}
}
