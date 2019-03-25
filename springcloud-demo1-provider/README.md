一、 单体架构的缺点
	1. 复杂性高
		* 模块多
		* 模块边界模糊
		* 依赖关系不清晰
		* 代码质量参差不齐
	2. 技术债务
		* “不坏不改”思想，导致代码越来越难以修改。
	3. 部署频率低
		* 部署时间长、范围大（整个web应用）、风险高。使得版本发布频率低，每次版本修改一大堆功能。
	4. 扩展能力受限
		* 例如一个模块的功能是计算密集型，需要强劲的CPU，而另外一个模块是IO密集型需要更大的内存，部署在
			一块就必须一方妥协。
	5. 阻碍技术创新
		* 因为应用大，所以重构、引入新技术成本都很高。（100W行代码，把struts2换成springMVC）
二、微服务架构
	1. 特点
		* 每个微服务可以独立运行在自己的进程
		* 一系列独立运行的微服务组成整个系统
		* 每个服务为独立的业务开发，一个微服务只关注某个特定的功能
		* 微服务之间可以通过一些轻量的通信机制进行通信，例如REST API
		* 可以使用不同语言和数据存储技术
		* 全自动的部署机制
	2. 优点
		* 易于开发和维护
		* 单个微服务启动较快
		* 局部修改易部署
		* 技术栈不受限
		* 按需收缩
			- 如果某个微服务遇到瓶颈，我们可以结合微服务业务特点，增加内存、升级CPU、增加结点
	3. 缺点
		* 运维要求高
			- 要保证几十，上百个微服务正常运作和协作
			- 维护一个war包和一个100个war包
			- 定位问题
		* 分布式固有复杂性
			- 系统容错
			- 网络延迟
			- 分布式事务
		* 接口调整成本高
			- 如果某个服务的API修改，那么调用它的微服务都需要修改
		* 重复劳动
			- 很多微服务可能功能相同，但这个功能没有到分解成一个微服务的程度，这个是否每个服务都会开发相同的
				功能，导致代码重复。
	4. 微服务设计原则
		* 单一职责原则
		* 服务自治原则（最重要）
			- 每个微服务应该具备独立的业务能力、依赖与运行环境（独立的开发、运行、测试、数据库）
		* 轻量级通信原则
		* 接口明确原则
			- 接口定义明确，尽量保持不变
三、SpringCloud简介
	1. 定义
		* SpringCloud是开发分布式系统的工具集
	2. 版本：
		* 1.4.0-RELEASE
		* 大版本：软件的代数。1表示第一代
		* 次版本（小版本）：对大版本的功能增强和bug修复，4表示功能增强，bug修复次数
		* 增量版本：bug修复次数，0表示对1.4进行0次修复
		* 里程碑版本：RELEASE、SNAPSHOT
			- RELEASE:正式版
			- SNAPSHOT:快照版，开发版
			- ...
	3. 使用spring boot套路构建微服务
		* 加pom.xml需要的微服务组件
		* 启动类上加注解
		* 修改/增加application.properties/application.yml的配置
	4. 使用SpringCloud所需要的知识
		* Java/Scala/Groovy
		* 构建工具
			- Maven
			- Gradle（安卓）
				maven转gradle项目命令：
				pom文件目录下输入：gradle init --type pom
		* Spring boot
	5. SpringCloud特点
		* 约定优于配置（spring boot特点）
		* 开箱即用、快速启动（spring boot特点）
		* 适用于各种环境（部署在PC上，部署在云上（阿里云...）,部署在容器（docker））
		* 轻量级组件
		* 组件的支持丰富、功能齐全
			- 注册中心
			- 配置中心
			- 只能路由
		* 选型中立
			- 例如服务发现组件可以选择Eureka、Zookeeper、Consul
四、服务提供者和服务调用者
	1. 服务提供者
		* 创建项目
			- devtools
			- web
			- mybatis
			- mysql
		* 其他依赖
			- fastjson
			- pageHelper
		* 使用yml，不使用properties
			- 有提示，方便
			- 有结构，好看
			- yml有严格的缩进，不能随便加空格
		* 配置yml
			- 数据库
			- mybatis
			- 配置端口号
		* 配置类
			- mybatis
			- pagehelper
		* 启动类配置
			- fastjson
		* 创建实体类
		* 创建mapper、mapper.xml
		* 创建service
		* 创建controller
		* 测试
			- http://localhost:7900/user/id/4
	2. 服务消费者
		* 创建项目
			- devtools
			- web
		* 其他依赖
			- fastjson
		* 配置yml
			- 配置端口号
		* 启动类配置
			- fastjson
			- RestTemplate
				@Bean
				public RestTemplate restTemplate() {
					return new RestTemplate();
				}
				> 配置类的@Bean
					@Bean
					public T name(){return t;}
					spring容器会创建这个类，等于： T name = new T();
		* 创建controller
			- 使用RestTemplate调用服务提供者
				@Resource
				private RestTemplate restTemplate;
				
				@GetMapping("/user/id/{id}")
				public User findById(long id) {
					return restTemplate.getForObject("http://localhost:7900/user/id/" + id, User.class);
				}
			- RestTemplate
				> GET
					-> getForEntity
						<> 以get方式请求url，把响应封装成ResponseEntity<T>
						<> 返回值是ResponseEntity<T>。ResponseEntity<T>是Spring对HTTP请求响应的封装，
							包括了几个重要的元素，如响应码、contentType、contentLength、响应消息体等
					-> getForObject
						<> 只返回getForEntity的响应体，忽略了状态码，响应头
				> POST
					-> postForEntity
					-> postForObject
					-> postForLocation
						<> postForLocation也是提交新资源，提交成功之后，返回新资源的URI，postForLocation的参数和
							前面两种的参数基本一致，只不过该方法的返回值为Uri，这个只需要服务提供者返回一个Uri即可，
							该Uri表示新资源的位置。
				> PUT
					-> putForEntity
					-> putForObject
					-> 占位符
						get/put/(url,clazz,obj...)
						restTemplate.put("http://HELLO-SERVICE/getbook3/{1}", book, 99);
				> DELETE
					-> deleteForEntity
					-> deleteForObject
				> 注意，返回list类型，java中强转会报错，也可以可以正常显示，正确做法是返回对象数组
					// 页面可以显示，但是在java代码中会强转错误
					List<User> list = this.restTemplate.getForObject(vip, List.class);
					for (User user : list) {
						// 强制转换异常
						System.out.println(user.getId());
					}
					正确方式如下：
					User[] arr = this.restTemplate.getForObject(vip, User[].class);
					List<User> list = new ArrayList<User>();
					for (User user : arr) {
						System.out.println(user.getId());
						list.add(user);
					}
		* 创建实体类（和服务提供者的实体类一样，从服务提供者复制过来）
		* 测试
			- 地址栏输入：http://localhost:7901/movie/user/id/4
			- 请求到user微服务，http://localhost:7900/user/id/4
			- 返回id=4的user
		* fastjson乱码问题
			- 不要在启动类使用@Bean的方式
			- 创建WebMvcConfig实现WebMvcConfigurer，重写
				@Override
				public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
					FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
					FastJsonConfig config = new FastJsonConfig();
					config.setSerializerFeatures(SerializerFeature.PrettyFormat);
					List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
					fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
					converter.setSupportedMediaTypes(fastMediaTypes);
					converter.setFastJsonConfig(config);
					converters.add(converter);
				}
五、微服务的注册与发现
	1. 直接使用restTemplate调用的问题
		* 服务的ip改变了，要么要修改源代码，要么要修改配置文件，多个微服务就基本全部都要修改一遍。
	2. 服务的注册与发现这种机制的目的是为了降低分布式系统的复杂性
	3. 角色关系
		* 服务消费者：服务的调用方叫做服务消费者，也就是调用微服务的微服务。
		* 服务提供者：服务的被调用方叫做服务提供者，也就是提供API的微服务。
	4. 注册中心（服务发现组件）
		* A服务调用B服务，需要ip和端口， A服务不能硬编码写死ip和端口（指到Nginx，反向代理，指向服务提供者），
			所以需要服务发现组件（注册中心）
		* 注册中心作用：
			- 服务提供者启动的时候，都会把自己的ip和端口发送到服务发现组件，服务消费者通过服务发现组件查询得到
				服务提供者的ip和端口（本地有缓存就直接获取缓存的ip和端口），然后再调用微服务。
			- 心跳机制
				每个微服务都需要向注册中心发送心跳，判断服务是否挂掉
		* 注册中心结构
			- 服务注册表
				存储服务，服务的ip和端口
			- 服务注册
				> 把服务添加到服务注册表的动作叫做服务注册
				> 把服务从服务注册表移除的动作叫做服务注销 
			- 健康检查
				> 心跳机制，或者其他机制，检测服务能不能够访问，如果不能够访问则注销服务，
					从而使服务消费者能够正常调用服务提供者
	5. 服务发现
		http://blog.daocloud.io/microservices-4/
		* 客户端发现
			- Eureka、Zookeeper
			- 使用客户端发现模式时，客户端决定相应服务实例的网络位置，并且对请求实现负载均衡。
				客户端查询服务注册表，后者是一个可用服务实例的数据库；然后使用负载均衡算法从中选择一个实例，并发出请求。
			- 服务实例的网络位置在启动时被记录到服务注册表，等实例终止时被删除。服务实例的注册信息通常使用心跳机制来定期刷新。
			- 客户端发现模式优缺点兼有。这一模式相对直接，除了服务注册外，其它部分无需变动。
				此外，由于客户端知晓可用的服务实例，能针对特定应用实现智能负载均衡，比如使用哈希一致性。
				这种模式的一大缺点就是客户端与服务注册绑定，要针对服务端用到的每个编程语言和框架，实现客户端的服务发现逻辑。
		* 服务器端发现
			- Consul + Nginx
			- 客户端通过负载均衡器向某个服务提出请求，负载均衡器查询服务注册表，并将请求转发到可用的服务实例。
				如同客户端发现，服务实例在服务注册表中注册或注销。
		* 客户端发现和服务器端发现的区别
			- 客户端发现是：
				1. 客户端访问注册中心
				2. 客户端调用服务
			- 服务器端发现是：
				1. 客户端访问负载均衡器
				2. 负载均衡器访问注册中心
				3. 负载均衡器调用服务
	5. Eureka
		* 介绍
			- Eureka是Netflix开发的服务发现框架，本身是一个基于REST的服务，主要用于定位运行在AWS(Amazon Web Services)域中的中间层服务，
				以达到负载均衡和中间层服务故障转移的目的。
		* 原理
			- region（区域）和available zone（可用区）的概念
				> https://bbs.csdn.net/topics/390800408
				> 先来看一下区域（Region）概念。AWS云服务在全球不同的地方都有数据中心，比如北美、南美、欧洲和亚洲等。
					与此对应，根据地理位置我们把某个地区的基础设施服务集合称为一个区域。通过AWS的区域，一方面可以使得AWS云服务
					在地理位置上更加靠近我们的用户，另一方面使得用户可以选择不同的区域存储他们的数据以满足法规遵循方面的要求。
					在12月18日发布会之前全球有9个区域，包括：美东（北佛吉尼亚）、美西（俄勒冈）、美西（北加利佛尼亚）、
					欧洲（爱尔兰）、亚太（新加坡）、亚太（东京）、亚太（悉尼）、南美（圣保罗）和在美西服务政府的GovCloud区域。
					AWS中国（北京）区域将是亚马逊AWS在亚太地区的第4个区域，同时也是全球范围内的第10个区域。
				> AWS的每个区域一般由多个可用区（AZ）组成，而一个可用区一般是由多个数据中心组成。
					AWS引入可用区设计主要是为了提升用户应用程序的高可用性。因为可用区与可用区之间在设计上是相互独立的，
					也就是说它们会有独立的供电、独立的网络等，这样假如一个可用区出现问题时也不会影响另外的可用区。
					在一个区域内，可用区与可用区之间是通过高速网络连接，从而保证有很低的延时。
			- 服务消费者、服务提供者，都有一个Eureka客户端，都与Eureka服务器端交互，Eureka记录它们的信息（ip、端口）到
				服务注册表。注册后，Eureka客户端和服务器端有心跳机制，默认时间为30秒，没30秒发送一个心跳包。如果服务器端
				连续3个心跳周期都没收到客户端的心跳包，就注销这个服务。
			- Eureka通过心跳检测、健康检查、缓存机制，确保了系统的高可用性、灵活性（服务挂掉或ip修改，服务器会更新）、
				伸缩性（Eureka可以集群）
		* 实现Eureka Server
			- 创建项目（Eureka Server、devtools、Spring Security）
				> 镜像都没有，所需下载jar包需要下载很久或者下载失败
			- 启动类加上注解@EnableEureka注解
			- 配置yml
				> 配置端口号
					默认8761
				> 禁止当前节点为客户端
					<> 在Eureka集群中需要互相通信，所以Eureka服务器端也是一个客户端。在单机模式禁用作为客户端。
				> 配置默eureka服务器认访问路径
					server:
					  port: 8761
					eureka:
					  client:
					    service-url: 
					      defaultZone: http://localhost:8761/eureka
					    register-with-eureka: false
					    fetch-registry: false
				> 给eureka server添加验证
					<> 添加spring security依赖
					<> 配置spring security的name和password
						spring:
						  security:
						    user:
						      name: user
						      password: psw123
					<> 修改defaultZone
						defaultZone: http://user:psw123@localhost:8761/eureka
			- 启动项目
			- 地址栏输入“http://localhost:8761/”进入eureka页面
		* 把微服务注册到Eureka Server上
			- 创建微服务项目：web、mybatis、mysql、devtools、eureka discovery
			- 其他依赖：fastjson、pageHelper
			- 配置文件
				> yml
					<> 配置eureka服务器的url
						eureka:
						  client:
						    service-url:
						      defaultZone: http://localhost:8761/eureka # 服务器没有spring security
						      defaultZone:http://user:psw123@localhost:8761/eureka # 服务器有spring security
					<> 指定微服务的名称
						spring:
							application:
    							name: microservices-provider-user # 名称建议用小写
    				<> 指定微服务的url
    					[] eureka.instance.prefer-ip-address=true注册到Eureka Server上的是IP，
    						如果是false则是主机名，默认是false。
    					[] eureka.instance.prefer-ip-address=true时spring就会自动为我们获取第一个非回环IP地址。
    					[] eureka.instance.ip-address这个配置是手动配置ip地址。
    					[] 如果eureka.instance.prefer-ip-address=true和eureka.instance.ip-address
    						同时配置那么是使用手动配置ip，而不是自动获取第一个非环回地址ip
    				<> 指定instance-id为“微服务名称:端口号”
    					[] instance-id默认为“主机名:微服务名称:端口号”
    					[] eureka.client.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${server.port}}
    				<> eureka server添加的spring security验证，客户端需要添加用户名和密码
    					defaultZone:http://user:psw123@localhost:8761/eureka
    					[] 添加依赖（监控、管理生产环境）
    						<dependency>
								<groupId>org.springframework.boot</groupId>
								<artifactId>spring-boot-starter-actuator</artifactId>
							</dependency>
    					[] “http://登录用户名:登录密码@ip:端口/eureka”这种风格的ip叫做curl style 
    				<> 健康检查
    					默认情况下注册到eureka server的服务是通过心跳来告知自己是UP还是DOWN，
    					并不是通过spring-boot-actuator模块的/health端点来实现的，这样其实不是很合理。
						
						默认的心跳实现方式可以有效的检查eureka客户端进程是否正常运作，但是无法保证客户端应用能够正常提供服务。
						由于大多数微服务应用都会有一些其他的外部资源依赖，比如数据库，REDIS缓存等，如果我们的应用与这些外部资
						源无法连通的时候，实际上已经不能提供正常的对外服务了，但因为客户端心跳依然在运行，所以它还是会被服务消
						费者调用，而这样的调用实际上并不能获得预期的后果。
						
						我们可以通过在eureka客户端中配置:eureka.client.healthcheck.enabled=true，
						就可以改变eureka server对客户端健康检测的方式，改用actuator的/health端点来检测。
						
						[] 添加依赖spring-boot-starter-actuator
						[] 地址栏输入：http://localhost:7900/actuator/health
							显示{"status":"UP"}或者{"status":"DOWN"}
						[] 参考：https://www.cnblogs.com/EasonJim/p/7613333.html
				> mapper.xml
			- 配置类
			- 启动类
				> 加上@EnableEurekaClient注解
					这个注解只是eureka可用
				> 加上@EnableDiscoveryClient
					通用的注册服务注解，eureka、zookeeper都可以用
			- 启动微服务
			- 打开eureka服务器，看是否多了已注册的服务http://localhost:8761/
			- eureka客户端调用微服务
				> controller 注入EurekaClient
					InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("microservices-provider-user", false);
					String url = instanceInfo.getHomePageUrl()
					得到指定url
				> controller注入DiscoveryClient
					1. 依赖注入DiscoveryClient
					2. 通过DiscoveryClient获取指定微服务的ip
					3. 通过restTemplate和微服务ip和端口访问微服务
					List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("microservices-provider-user");
					serviceInstanceList.get(0).getUrl()得到指定url，restTemplate进行远程调用
		* Eureka存储元数据(metadata)
			- yml配置eureka.instance.metadata-map.xxx = yyy
			- xxx可以是eureka自带的key（例如zone），也可以是自己定义的key（demo8）
			- 地址栏输入：
				http://localhost:8761/eureka/apps/MICROSERVICES-CONSUMER-MOVIE
				可以看到metadata
			- 可以获取ServiceInstance，从而获取到实例的元数据
		* 集群(demo8)
			- 演示时去掉devtool，不然一修改yml配置就会重新启动，就不能1个应用启动3个端口
			- eclipse启动不能一个yml配置3个eureka服务器
			- 把3个服务器写在一个yml，要用cmd命令启动
				maven install server和client
				java -jar springcloud-demo8-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
				java -jar springcloud-demo8-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
				java -jar springcloud-demo8-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer3
				java -jar springcloud-demo8-eureka-feign-consumer-0.0.1-SNAPSHOT.jar 
			- 消费者微服务会同时注册到3个server
			- 3个eureka server互相注册到对方
			- eureka客户端的defaultZone配置3个url，以防一个server挂掉了导致注册不到服务
				eureka:
				  client:
				    service-url: 
				      default-zone: http://localhost:8761/eureka,http://localhost:8762/eureka,http://localhost:8762/eureka
		* eureka常用配置
			- spring cloud所有配置：
				http://cloud.spring.io/spring-cloud-static/Finchley.M8/multi/multi__appendix_compendium_of_configuration_properties.html
			- eureka.dashboard.enabled
				是否显示首页，默认为true。false的话输入http://localhost:8761/报404
			- eureka.dashboard.path
				首页路径默认为"/" ，例如输入http://localhost:8761/就可以访问
				改为/aaa,要输入http://localhost:8761/aaa/才能访问
				> 应用场景，不让人猜到服务路径
				> 按模块划分server
			- eureka.instance.appname
				> 设置微服务的名称，是最原本注册到eureka的服务名称。
				> 如果没有设置这个值，那么如果配了spring.application.name(serviceid)设置的值，
					就为spring.application.name；如果没有设置spring.application.name，默认为UNKNOW
				> 使用场景
					spring.application.name和swagger冲突，首页的服务名称UNKNOWN，
					要用eureka.instance.appname。
				> swagger
					随着前后端分离，API成了连接前后端的唯一路径。为了解放生产力，不在手写API文档，于是
					有了生成API文档的工具。swagger和rap都是API文档生成工具
			- eureka.instance.namespace
				> 默认为eureka， 这个配置在spring cloud中没有用
			- eureka.instance.non-secure-port
				> 非https端口，默认80
			- eureka.datacenter = cloud
			- eureka.environment = product
				eureka:
			  	  environment: product # eureka主页的system status environment从test改为produck
			  	  datacenter: cloud # eureka主页的system status datacenter从default改为cloud
			- 删除微服务结点间隔
				eureka:
				  server:
				    enable-self-preservation: false # 禁用自我保护模式
				    eviction-interval-timer-in-ms: 60000 # 清理间隔，默认60000（1分钟）
		* eureka自我保护模式
			- 自我保护模式时，主页有红色的字。此时如果服务器超过90秒没接收到客户端的心跳也不会把客户端从服务注册表中删除
			- 自我保护模式主要用于一组客户端和Eureka Server之间的网络分区情况
			- 关掉微服务后，理论上是eureka server90秒没收到心跳，然后从服务注册表删除掉这个微服务。但是STS停止应用
				会调用Spring boot shutdown hook，主动让微服务下线。eureka server主页的微服务就立刻没有了。
			- 禁用eureka的自我保护模式
				eureka.server.ebable-self-preservation = false
六、调用微服务
	1. 客户端负载均衡Ribbon
		* 客户端负载均衡
			- 根据多个服务提供者发送过来的消息，进行负载均衡方案
		* 服务器端负载均衡
			- 在注册中心和服务提供者实例之间进行负载均衡
		* Ribbon原理
			- 向注册中心查询服务提供者结点，查出一个列表（多个微服务），通过负载均衡算法，命中服务提供者结点。
		* 创建项目（devtools、web、eureka discovery）
			- eureka自带ribbon，所以可以不用添加ribbon依赖
		* getRestTemplate()添加@LoadBalanced注解
		* restTemplate.getForObject的url参数，把ip地址和端口号改为微服务名称。
			"http://localhost:7900/user/id/" + id
			改为
			"http://microservices-provider-user/user/id/"+id
			User user = restTemplate.getForObject("http://microservices-provider-user/user/id/"+id, User.class);
		* 测试
			地址栏输入：http://localhost:7901/movie/user/id/2
			第1,2,3,6次，命中7902, 第4,5次命中7900
		* 负载均衡策略
			- ribbon默认负载均衡策略为轮询
		* 定制ribbon客户端
			- 通过代码自定义ribbon客户端
				> 创建配置类加上@Configuration注解，添加以下配置
					@Bean
					public IRule ribbonRule(IClientConfig config) {
						// RibbonClientConfiguration的ribbonRule是使用忽视zone的rule
						return new RandomRule(); // 打断点，地址栏输入http://localhost:7901/movie/user/id/3，则进入断点
					}
				> 注意，上面那个配置类不能再被@ComponentScan扫描到和@SpringBootApplication子包下，
					否则所有ribbon client都会使用这个配置（启动就执行ribbonRule方法，不在注解扫描下是调用才执行
					ribbonRule方法，可以通过打断点验证）。
					- 解决方法1
						把配置类移到SpringBootApplication包外
					- 解决方法2
						1. 写一个注解A
						2. 配置类仍在SpringBootApplication同包和子包下，但是加上这个注解A
						3. 启动类加上注解@ComponentScan(excludeFilters = {@ComponenetScan.Filter(type = FilterType.ANNOTATION, value=A.class)})
					- 类似的问题（父子容器都扫描这个包）
						- springmvc.xml和applicationContext.xml配置自动扫描，扫描包重复的话事务注解不起作用。
				> 第1，5次命中7902，第2,3,4次命中7900
				> 使用LoadBalancerClient
					Controller注入LoadBalancerClient，通过LoadBalancerClient.choose(serviceId),
					ribbon客户端通过负载均衡命中这个serviceId的一个实例，返回ServiceInstance。
					ServiceInstance可以获得这个微服务实例的ip、port、serviceId等信息
					<> demo3地址栏输入：http://localhost:7901/movie/user/listByPager/1/2
					<> 输出：
						192.168.31.227:7902:microservices-provider-user
						192.168.31.227:7902:microservices-provider-user
						192.168.31.227:7900:microservices-provider-user
						192.168.31.227:7900:microservices-provider-user
						192.168.31.227:7900:microservices-provider-user
						192.168.31.227:7902:microservices-provider-user
						192.168.31.227:7902:microservices-provider-user
			- 通过配置文件自定义ribbon客户端
				> 配置文件配置比@Configuration配置的优先级高，也高于Spring Cloud Nieflix的默认配置
				> yml文件添加以下配置，其中microservices-provider-user是要调用的微服务的serviceId
					microservices-provider-user:
					  ribbon:
					    NLoadBanlancerRuleClassName = com.netflix.loadbalancer.WeightedResponseTimeRule # 响应时间权重
 		* Ribbon脱离Eureka单独使用
 			- ribbon不使用eureka服务器注册的信息，使用自己配置的信息
 			- classpath有eureka，需要禁用eureka
 				ribbon:
 				  eureka:
 				    enabled: false
 			- 添加ribbon的配置
 				microservices-provider-user:
 				  ribbon:
 				  	listOfServers: localhost:7900,localhost:7902(配置多个只命中第一个)
 			- 直接使用ribbon API：LoadBalancerClient
 				
	2. 声明式的REST Client：Feign
		* 简化Java Http客户端的工具
		* 可以简单的实现REST API后面的一堆参数
		* 自带ribbon
		* 创建项目（devtools、web、eureka discovery、feign）
		* 启动类增加@EnableFeignClients注解
		* 编写UserFeignClient接口，
			- 接口上添加FeignClient注解，指定serviceId
			- 添加方法给controlelr调用
			- 方法上添加@RequestMapping注解指定请求服务url的方法(SAVE、DELETE、PUT、GET、)和路径，路径为服务提供者指定方法的路径
			- 参数添加@PathVariable
			- 注意
				> (问题已修复)Feign中@GetMapping不支持，要用RequestMapping(method = HttpMethod.GET)
				> (问题未修复)Feign中@PathVariable注解一定要设置value的值，否则报错
				> (问题未修复)即使指定了GET方法RequestMapping(method = HttpMethod.GET)，但是如果
					接口的参数是对象，仍是使用POST方法访问服务提供者。如果一定要用GET方法的话，只能把对象拆成String和基本类型
						public User modName(User user);//即使没有@RequestBody仍使用POST方法提交
					改为
						public User modName(@PahVariable("id") long id, @PahVariable(name) String name,...);
				> 虚拟ip转成eureka上真正的ip可能会超时
				> 测试时没打开数据库服务会超时
				> Feign暂时不支持复杂对象作为参数
		* Controller注入UserFeiClient，调用它的方法
		* (问题已修复)视频上feign第一次请求会timeout的问题。
			Hystrix默认的超时时间是1秒，如果超过这个时间尚未响应，将会进入fallback代码。而首次请求往往会比较慢（因为Spring的懒加载机制，要实例化一些类），这个响应时间可能就大于1秒了
			解决方案有三种，以feign为例。
			方法一
			hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000
			该配置是让Hystrix的超时时间改为5秒
			方法二
			hystrix.command.default.execution.timeout.enabled: false
			该配置，用于禁用Hystrix的超时时间
			方法三
			feign.hystrix.enabled: false
			该配置，用于索性禁用feign的hystrix。该做法除非一些特殊场景，不推荐使用。
		* 覆写feign默认配置
			- 使用@FeignClient("comuserServiceId")注解时，spring会为这个注解创建一个子容器，
				这个子容器包括feign.Encoder,feign.Decoder,feign.Contract(契约)
			- @FeignClient注解的configuration属性，指定配置类，默认是FeignClientsConfiguration
			- 注意：
				> @FeignClient注解的configuration属性指定的配置类不能在@ComponentScan或者
					@SpringBootApplication的同包和子包下，否则会导致所有的FeignClient都使用这个配置
				> @FeignClient(name="serviceId",url="url")
					如果配置url，则必须配置name
			- feign的默认配置 
				> Decoder：ResponseEntityDecoder
				> Encoder：SpringEncoder
				> Logger：Slf4jLogger
				> Contract：SpringMvcContract // 所以可以使用springmvc的注解
				> Feign.Builder：HystrixFeign.Builder
				> Client：如果Ribbon没被禁用则使用LoadBalancerFeignClient，否则使用默认的feign client.
					使用OKHttpClient和ApacheHttpClient的配置为：
						feign.okhttp.enabled = true
					     和  feign.httpclient.enabled = true
			- 使用Feign默认的注解，而不使用SpringMVC的注解
				> 创建配置类，放在@ComponentScan或者@SpringBootApplication的同包和子包外
				> 配置类添加下面方法
					@Bean
					public Contract feignContract() {
						/*
						 * 不能在使用SpringMVC的注解，要使用Feign的注解，@RequestLine
						 */
						return new feign.Contract.Default();
					}
				> 添加@Conguration注解
				> @FeignClient注解上添加conguration=xxx.class
				> @FeignClient注解的接口不能使用SpringMVC的注解。
					<> 请求方式和请求路径，参数，实体类参数形式如下
					@RequestLine("GET /user/id/{id}")
					public User findById(@Param("id") long id);
					
					@RequestLine("POST /user/modName")
					public User modName(User user);
			- 配置打印日志（无法成功）
				> yml配置
					logger:
					  level:
					    feignClient全限定名: DEBUG
				> 配置类注入FEIGN.LEVEL
					@Bean
					public Feign.LEVEL xx(){ return Feign.LEVEL.FULL; }
					
七、微服务容错
	1. 雪崩效应
		* C、D微服务调用B微服务、B微服务调用A微服务。A微服务挂掉，B微服务调用A微服务就会线程阻塞，然后B一直等待也挂掉了，
			C、D同理也挂掉了。
	2. 容错方案
		* 设置超时时间
		* 熔断器模式
	3. 断路器的状态
		* 关闭：正常状态，可以调用微服务
		* 打开：不请求微服务，直接返回默认信息或异常
		* 半开：一部分流浪直接返回默认信息或异常，另一部分流量尝试连接微服务
	4. 断路器原理
		* 监控，监控微服务B调用微服务A成功多少次，失败多少次。失败率是否达到阈值，如果达到阈值，打开断路器
		* 断路器的状态：打开，关闭，半开
		* 状态为半开时的分流算法
		* 自我修复：分流时发现失败率不满阈值，断路器状态从半开改为关闭
	5. Hystrix默认5秒内失败20次打开断路器
		* hystrix提供fallback回退API给开发人员调用
	7. javanica
		* Hystrix的一个子项目，为了简化Hystrix的编码
	8. 熔断器放在服务消费者
	9. 创建项目（Web、Ribbnon、Hystrix、Hystrix Dashboard(微服务调用监控)、Eureka Discovery、devtools、actuator）
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>
	10. 启动类加@EnableCircuitBreaker
	11. 方法上加@HystrixCommand(fallBackMethod="func")注解，指定这个方法短路后的执行的回退方法
	12. 在同一个Controller（MovieController）添加func方法，要求要和被@HystrixCommand注解的方法
		同参数，同返回值
	13. 测试
		* 正常访问
		* 修改调用的serviceId，错误的访问，执行fallback方法，返回了空user
		* 消费者的方法去掉@HystrixCommand注解，通过错误路径访问微服务，报500
			http://localhost:7901/movie/user/id/2?ver=dd
	14. 配了Hystrix第一次调用方法，即使正常也会调用fallback方法
		* 因为hystrix默认超时时间为1秒，第一次执行时间比较长
		* 配置
			hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000 
	15. Hystrix的隔离策略
		* @HystricxCommand注解中HystricxCommand.run()的执行策略
			- THREAD(线程)，HystricxCommand.run()的执行会被最大线程数限制
				正常的controller方法调用是一个线程，fallback方法是另外一个线程调用
			- SEMAPHORE(信号量)，HystricxCommand.run()的执行会被最大信号量的个数限制
				正常的controller方法和失败时调用的fallback方法会在一个线程上运行
			- 默认，推荐配置为THREAD
			- 什么时候用SEMAPHORE？
				> 当controller使用了@SessionScope或者@RequestScope注解时，使用Thread报运行时异常，
					找不到上下文才需要配置为SEMAPHORE
			- 配置
				@HystrixCommand(fallbackMethod="findByIdFallback", 
				commandProperties= @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"))
	16. 查看健康检查里的Hystrix
	17. http://localhost:8010/hystrix.stream
		- 添加配置了，增加url映射
		@Configuration
		public class HystirxConfig {
		
		    //用来像监控中心Dashboard发送stream信息
		    @Bean
		    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
		        ServletRegistrationBean<HystrixMetricsStreamServlet> registration = new ServletRegistrationBean<HystrixMetricsStreamServlet>(new HystrixMetricsStreamServlet());
		        registration.addUrlMappings("/hystrix.stream");
		        return registration;
		    }
		}
	18. Feign对Hystrix的支持
		* 不像ribbon有对javanica的支持，Fegin不能直接使用@HystrixCommand(fallBackMethod="func")
		* 如果classpath下有Hystrix，Fegin将使用短路器
		* Feign使用Hystrix需要创建一个配置类，继承HystixCommand
		* 创建项目（devtools、web、eureka discovery、feign、hystrix、actuator）
		* yml配置feign对hystrix的支持
			- feign.hystrix.enbaled: true
			- feign.hystrix.enbaled: false 全局禁用hystrix
		* 启动类配置@EnableCircuitBreaker
		* @FeignClient注解使用fallback属性指定失败时调用的类
			- 注意FeignClient接口上不能有@RequestMapping注解，否则使用hystrix会报错
		* 创建失败调用的类，这个类需要添加@Component注解，继承被@FeignClient注解的接口，实现接口的方法。
			这些方法就是断路器打开时执行对应的方法。
	19. Feign使用fallbackFactory
		* @FeignClient的fallback属性和fallbackFactory属性冲突，都配置的话使用fallback的断路逻辑
		* 在@FeignClient的类里面创建一个静态内部类，这个静态内部类实现FallbackFactory<@FeignClient注解的接口>接口，
			并且这个静态内部类加上@Component注解
		* 实现这个工厂类的T crate()方法
		* create方法里面返回一个继承了@FeignClient的接口的接口的实例
			
			@FeignClient(name="microservices-provider-user"
			// fallback和fallbackFactory有冲突，都配的话使用fallback
			//, fallback=UserFeignClientFallback.class
			, fallbackFactory=UserFeignClientFallbackFactory.class)
			public interface UserFeignClient {
			
				@GetMapping("/user/id/{id}")
				public User findById(@PathVariable("id") long id);
				
				// 创建UserFeignClient的fallback工厂
				@Component
				static class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient>{
					
					private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientFallbackFactory.class);
					
					@Override
					public UserFeignClient create(Throwable cause) {
						// 打印日志， slf4j打印日志支持占位符
						LOGGER.info("fallback reason was : {}" , cause.getMessage()); 
						// 匿名类转换成lambda表达式
						return new UserFeignClientWithFactory() {
			
							@Override
							public User findById(long id) {
								System.out.println(this.getClass().getName());
								User user = new User();
								user.setId(-2L);
								user.setName("UserFeignClientFallbackFactory.create().UserFeignClientWithFactory.findById()");
								return user;
							}
							
						};
					}
				}
				
			}
			
			// 实现UserFeignClient里面的方法，这里实现的方法就是断路器打开时执行的方法
			interface UserFeignClientWithFactory extends UserFeignClient{
				
			}
	20. 注意
		* 目前Feign的Hystrix的Fallback不支持返回HystrixCommand类和rxjava的rx.Observable的方法
	21. 禁用单个Feign对Hystrix的支持
		- 创建配置类，并添加@Configuration注解。
			- 注意不能重复扫描
		- 配置类添加下面方法
			@Bean
			@Scope("prototype")
			public Feign.Builder feignBuilder(){
				/*
				 * 如果启用了hystrix默认是HystrixFeign.builder();
				 * 如果直接返回Feign.builder()就没有对hystrix支持，配置了这个类的FeignClient就不支持对hystrix的支持
				 */
				//HystrixFeign.builder();
				return Feign.builder();
			}
		- 要禁用的Hystrix的FeignClient的@FeignClient注解上添加configuation=xxConfiguration.class
		- eureka服务关闭后也是会打开断路器
	22. Hystrix的Dashboard
		* Hystrix的Dashboard可以图文显示每个断路器的健康状况和每个Hystrix命令的信息
		* 创建项目，只添加hystrix dashboard
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
			</dependency>
		* 启动类添加@EnableHystrixDashboard注解
		* yml配置端口号
			- server.port: 8030
			- 默认为8080
	 	* 输入地址“http://localhost:8030/hystrix”进入首页
	 	* 根据首页输入
	 		http://localhost:7903/hystrix.stream
	 		则把http://localhost:7903/hystrix.stream转为图表信息显示
	23. 函数式变成与响应式编程(了解)
		* 函数式编程
			- 纯函数
				> 相同的输入，结果总会得到相同的输出，而且没有任何可观察的副作用，也不依赖外部环境的状态。
				> http://blog.csdn.net/c_kite/article/details/79138814
			- 柯里化
				> 柯里化（Currying）是把接受多个参数的函数变换成接受一个单一参数(最初函数的第一个参数)的函数，并且返回接受余下的参数且返回结果的新函数的技术。
				> 函数柯里化(currying)又可叫部分求值。一个currying的函数接收一些参数，接收了这些参数之后，
					该函数并不是立即求值，而是继续返回另一个函数，刚才传入的参数在函数形成的闭包中被保存起来，
					待到函数真正需要求值的时候，之前传入的所有参数都能用于求值。 
				> 优点
					<> 可以方便地用一般化函数来造特殊函数
					<> 更加自然地处理参数。比如不确定参数的数目->确定参数的数目
				> 缺点
					<> 极少数情况下会让程序员搞不清参数的数目
		* 响应式编程
			- 1. a=b+c; 2. b或者c的值改变会影响到a，即使a在b、c之前就已经算出值了
			- 发布/订阅 
	24. Windows->Preference->Java->Editor->Save Actions
		* 选择Perform the select actions on save
		* 选择Additional actions
			- 点击Configure...
			- 选中code style页
			- 选中functional interface intances
			- 选中Use lambda where possible
				保存时如果能用lambda表达式就自动转为lambda表达式
	25. Turbine
		* 用处
			- 聚合多个监控，监控一个微服务实例意义不大，需要监控整个集群
		* 创建项目，只选择Turbine
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-netflix-turbine</artifactId>
			</dependency>
		* 启动类添加@EnableTurbine注解
		* yml配置，把turbine注册到eureka上
		* yml配置turbine,监控同一个serviceid集群
			> 监控同一个serverid的集群
				- 配置如下
					turbine:
					  aggregator:
					    cluster-config:
					    - MICROSERVICES-CONSUMER-MOVIE 
					  app-config: microservices-consumer-movie
				- turbine.appConfig ：配置Eureka中的serviceId列表，表明监控哪些服务
				- turbine.aggregator.clusterConfig ：指定聚合哪些集群，多个使用”,”分割，默认为default。
					可使用http://.../turbine.stream?cluster={clusterConfig之一}访问
				- turbine.clusterNameExpression ：
					 1. clusterNameExpression指定集群名称，默认表达式appName；
					 	此时：turbine.aggregator.clusterConfig需要配置想要监控的应用名称；
					 2. 当clusterNameExpression: default时，turbine.aggregator.clusterConfig可以不写，
					 	因为默认就是default；
					 3. 当clusterNameExpression: metadata[‘cluster’]时，假
					 	设想要监控的应用配置了eureka.instance.metadata-map.cluster: ABC，则需要配置，
					 	同时turbine.aggregator.clusterConfig: ABC
			> 打开hystrix-dashboard应用，输入turbine的链接
				> http://localhost:8031/turbine.stream?cluster=MICROSERVICES-CONSUMER-MOVIE
				> cluster的值为cluster-config配的值，可以监控所有serviceId=MICROSERVICES-CONSUMER-MOVIE的实例
			> 监控有一定的延迟
		* 配置监控多个serviceid的微服务集群方法一(不推荐)
			> 有100个微服务就要配置100个serviceid
			> cluster-config和app-config加上其他微服务id
			> yml配置
				turbine:
				  aggregator:
				    cluster-config:
				    - MICROSERVICES-CONSUMER-MOVIE
				    - MICROSERVICES-PROVIDER-USER
				  app-config: microservices-consumer-movie,microservices-provider-user
		* 配置监控多个serviceid的微服务集群方法二(推荐)
			> yml
				turbine:
				  aggregator:
				    cluster-config:
				    - default
				  app-config: microservices-consumer-movie-feign,microservices-consumer-movie-ribbon
				  cluster-name-expression: "'default'" # 和new String("default")写法一样
			> dashboard输入http://localhost:8031/turbine.stream
				> 默认监控的是cluster-config = default的集群
		* 原理
			> turbine是将/hystrix.stream聚合起来
			> turbine注册到eureka之后，找到注册在eureka上的实例，然后把它们的homePageUrl加上/hystrix.stream
		* 微服务的contextPath修改了，导致actuator无法暴露/hystrix.stream
			> registration.addUrlMappings("/actuator/hystrix.stream");
		* 添加了registration.addUrlMappings("/actuator/hystrix.stream");之后，
			对应的hystrix.stream为http://localhost:7903/ribbon/hystrix.stream。
			turbine目前只能访问http://localhost:7903/hystrix.stream，报404，
			为了让turbine能访问，需要在turbine项目的yml配置
			> 根据微服务名称修改
				- turbine.instanceUrlSuffix.serviceId1: /ribbon/hystrix.stream
				- turbine.instanceUrlSuffix.serviceId2: /feign/hystrix.stream
			> 全局修改
				- turbine.instanceUrlSuffix: /feign/hystrix.stream
			> 修改后dashboard输入http://localhost:8731/turbine.stream?cluster=serviceId
				就有结果
		* 分离管理端口和业务端口
			> server.port是业务端口
				输入http://localhost:7903/movie/id/3
			> management.server.port是管理端口，actuator暴露的端点都在这个端口
				输入http://localhost:8013/actuator/hystrix.stream
			> turbine会自动识别默认的hystrix.stream为
				http://192.168.31.227:8013/actuator/hystrix.stream
八、API Gateway（服务网关）
	1.blog
		* http://blog.daocloud.io/microservices-2/
	2.目前存在问题
		* 客户端（网页、app）与微服务直接通信
			从理论上讲，客户端可以直接向每个微服务发送请求。
			每个微服务都有一个公开的端点(https ://.api.company.name）。该 URL 映射到微服务的负载均衡器，
			由后者负责在可用实例之间分发请求。为了获取产品详情，移动客户端将逐一向上文列出的 N 个服务发送请求。
			
			遗憾的是，这种方法存在挑战和局限。问题之一是客户端需求和每个微服务暴露的细粒度 API 不匹配。
			在这个例子中，客户端需要发送 7 个独立请求。在更复杂的应用程序中，可能要发送更多的请求；按照 Amazon 的说法，
			他们在显示他们的产品页面时就调用了数百个服务。然而，客户端通过 LAN 发送许多请求，这在公网上可能会很低效，
			在移动网络上就根本不可行。这种方法还使得客户端代码非常复杂。
			
			客户端直接调用微服务的另一个问题是，部分服务使用的协议对 web 并不友好。一个服务可能使用 Thrift 二进制 RPC，
			而另一个服务可能使用 AMQP 消息传递协议。不管哪种协议对于浏览器或防火墙都不够友好，最好是内部使用。
			在防火墙之外，应用程序应该使用诸如 HTTP 和 WebSocket 之类的协议。
			
			这种方法的另一个缺点是，它会使得微服务难以重构。随着时间推移，我们可能想要更改系统拆分成服务的方式。
			例如，我们可能合并两个服务，或者将一个服务拆分成两个或更多服务。然而，如果客户端与微服务直接通信，
			那么执行这类重构就非常困难了。
	3.使用 API 网关构建微服务 
		* 通常来说，使用 API 网关是更好的解决方式。API 网关是一个服务器，也可以说是进入系统的唯一节点。
			这与面向对象设计模式中的 Facade 模式很像。API 网关封装内部系统的架构，并且提供 API 给各个客户端。
			它还可能还具备授权、监控、负载均衡、缓存、请求分片和管理、静态响应处理等功能。
			由于上述三种问题的原因，客户端直接与服务器端通信的方式很少在实际中使用。
	4.API网关的优点和缺点
		* 优点
			> 使用 API 网关的最大优点是，它封装了应用程序的内部结构。客户端只需要同网关交互，而不必调用特定的服务。
				API 网关为每一类客户端提供了特定的 API，这减少了客户端与应用程序间的交互次数，还简化了客户端代码。
		* 缺点
			> 它增加了一个我们必须开发、部署和维护的高可用组件。还有一个风险是，API 网关变成了开发瓶颈。
				为了暴露每个微服务的端点，开发人员必须更新 API 网关。API网关的更新过程要尽可能地简单，这很重要；
				否则，为了更新网关，开发人员将不得不排队等待。
	5. Zuul简单配置
		* Zuul是基于JVM实现的路由器和服务器端的负载均衡器
		* 所有路由默认Hystrix的隔离策略为信号量（SEMAPHORE），可以使用
			"zuul.ribbonIsolationStrategy: THREAD"修改Hystrix隔离策略
		* Zuul启动器不包括发现客户端，例如没有包含Eureka Client，如果需要通过serviceId路由的话，需要
			添加服务发现依赖
		* 创建项目，添加Zuul和Eureka Discovery
			> Zuul的pom依赖包括了hystrix
		* 启动类添加@EnableZuulProxy注解，不需要添加@EnableDiscoveryClient注解
			> @EnableZuulProxy包括了@EnableDiscoveryClient和@EnbaleCircuitBreaker注解，
				也可以使用HystrixCommand
		* 配置yml
			> 和eureka client一样配置
		* 启动eureka server、demo2-provider、zuul
		* 地址栏输入http://localhost:8040/microservices-provider-user/user/id/3
			> 格式为http://zuulIp:zuulPort/要调用serviceId/调用service后面的路径
			> Mapped URL path [/microservices-provider-user/**] onto handler of type [class org.springframework.cloud.netflix.zuul.web.ZuulController]
		* yml配置url中serviceId的映射
			> http://localhost:8040/microservices-provider-user/user/id/3
				太麻烦，想改成
				http://localhost:8040/user/user/id/3
			> yml配置
				zuul:
				#  ignoredServices: '*' # 指定不反向代理的微服务，只有routes配了的微服务才反向代理。默认是*，反向代理所有微服务，如果不反向代理多个微服务，填serviceId，用逗号分隔开
				#  ignoredServices: microservices-provider-user # 地址栏输入http://localhost:8040/microservices-provider-user/user/id/1，报404
				  routes:
				    microservices-provider-user: /user/**
				    microservices-consumer-movie: /movie/**
			> 地址栏输入：
				http://localhost:8040/movie/movie/user/id/1
				http://localhost:8040/user/user/id/2
				也可以输入
				http://localhost:8040/microservices-consumer-movie/movie/user/id/1
				http://localhost:8040/microservices-provider-user/user/id/1
			> yml也可以如下配置
				- 1. 配置path和service-id
					zuul:
					  routes:
					    abc: # map 的key，不能重复
					      service-id: microservices-provider-user
					      path: /user/**
				- 2. 配置path和url
					zuul:
					  routes:
					    abc: # map 的key，不能重复
					      url: http://localhost:7900/
					      path: /user/**
					> 使用这种方式不会被ribbon负载均衡也不会被HystrixCommand执行
					> 通过以下配置可以执行ribbon负载均衡和执行hystrixCommand
						zuul:
						  routes:
						    echo:
						      path: /myusers/**
						      serviceId: myusers-service
						      stripPrefix: true
						
						hystrix:
						  command:
						    myusers-service:
						      execution:
						        isolation:
						          thread:
						            timeoutInMilliseconds: ...
						
						myusers-service:
						  ribbon:
						    NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
						    ListOfServers: http://example1.com,http://example2.com
						    ConnectTimeout: 1000
						    ReadTimeout: 3000
						    MaxTotalHttpConnections: 500
						    MaxConnectionsPerHost: 100
		* 使用正则表达式指定路由规则
			> 启动类添加下面代码
				@Bean
				public PatternServiceRouteMapper serviceRouteMapper() {
				    return new PatternServiceRouteMapper(
				        "(?<name>^.+)-(?<version>v.+$)",
				        "${version}/${name}");
				}
			> 意思
				- 微服务名称为microservices-provider-user-v1
					映射的url为
					http://localhost:8040/v2/microservice-provider-user/find/id/3
				- 如果serviceId不满足正则表达式，则按照默认情况
					http://localhost:8040/microservice-provider-user/find/id/3
					或http://localhost:8040/user/find/id/4
		* zuul.prefix
			zuul.prefix = /api
			输入地址从
			http://localhost:8040/user/find/id/5
			改为
			http://localhost:8040/api/user/find/id/5
		* zuul.stripPrefix
			> 默认为true，改为false的话
				- 访问地址从
				http://localhost:8040/api/user/find/id/5
				改为
				http://localhost:8040/api/find/id/5
				- 去掉了serviceid或者配置的path
				microservice-provider-user using LB returned Server: 192.168.31.227:7900 for request /api/find/id/5
			> 配合zuul.prefix，范围全局
		* zuul.routes.xx.stripPrefix
			> zuul.routes.xx.path，局部去掉微服务前缀
		* zuul.ignored-patterns
			> 忽略某些敏感路径，这个路径的就不路由
			> ignored-patterns: /**/admin/**
			> ignored-patterns: /**/page/**  # 过滤掉http://localhost:8040/api/user/page/2/2
		* zuul.routes.legacy
			> 这个只能在yml玩，不能在properties玩
			> 前面配置了zuul.routes.moduleA,zuul.routes.moduleB,剩下的用zuul.routes.legacy表示
			> zuul.routes.legacy: /**
		* 选择zuul运行的http客户端
			> 默认是Apache Http Client
			> ribbon.restclient.enabled=true 修改为Ribbon的RestClient
			> ribbon.okhttp.enabled=true 修改为okhttp3.OkHttpClient
		* zuul.routes.<route>.sensitiveHeaders
			> 有一些协议头不想传播到微服务，或者不想传到浏览器客户端，可以通过这个配置让网关到微服务的这段不传这些协议头。
			> 默认值是Cookie,Set-Cookie,Authorization
			> 如果网关后面的微服务是整个系统的一部分，可以移除掉这些协议头，如果有一些是微服务是别人的微服务，那么还是保留
				默认值，防止泄露用户信息。
		* zuul.ignore-security-headers
			> 意义：是否拦截spring security的headers
			> 默认值为true
			> 默认情况下，如果Spring Security不在类路径中，它们是空的，
				否则它们被初始化为Spring Security指定的一组“安全”头文件（例如涉及缓存）。
		* 查看端点信息
			> 需要引入actuator依赖，并且暴露/routes端点
			> http://localhost:8040/actuator/routes
			> 查看路由path对应的微服务
				- {"/api/user/**":"microservice-provider-user","/api/microservice-provider-user/**":"microservice-provider-user","/api/**":"legacy"}
		* 路由本地转发
			> 通过route2.path: forward:/route1, 实现在zuul本地转发
			> 通过本地路由转发实现Strangulation Patterns（Strangulation Patterns）
				- Strangulation Patterns,重构的过程中慢慢地把老系统分解
				zuul
				  route:
					service-id: microservices
					path: ...
				  legacy:  老系统
				  	path: /**  
				  	url: ...
					
			> 配置
				zuul:
				  routes:
				    user:
				      service-id: microservices-provider-user
				      path: /user/**
				    user1:
				      service-id: microservice-provider-user
				      path: forward:/user  # 本地转发，这个zuul的user1的路径转发到/user的路径，/<route>
				    legacy: /** # 除了已经配置的，剩下的路由路径
	6. 通过Zuul上传文件
		* 创建上传文件微服务（actuator、eureka discovery、web）
		* 配置yml
			> 上传文件大小限制200M
		* 编写Controller(FileUploadController)
		* 编写html(upload.html)
		* 上传文件，返回文件的绝对路径
		* 把html的action改为http://localhost:8040/api/uploadfile/upload
			- 上传文件报错because its size (222818091) exceeds the configured maximum (10485760)
			- zuul的yml也修改文件上传大小限制和请求大小限制，默认为10M， 设置为900M，比微服务配置的大
		* 加zuul前缀绕过Spring的DispatcherServlet，摆脱微服务端配置的文件大小限制。
			- 实际操作不能摆脱微服务的限制
			- http://localhost:8040/zuul/api/uploadfile/upload
		* 解决上传大文件超时问题
			hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 60000
			ribbon:
			  ConnectTimeout: 3000
			  ReadTimeout: 60000
		* 建议上传大文件加/zuul前缀，并设置超时时间
	7. @EnableZuulServer
		* 不包含任何反向代理的Zuul Server，是一个任何过滤器的空白的Zuul Server。
			- Zuul的很多功能都是通过过滤器实现的
		* 可以使用ZuulProperties自己配，实现自定义Zuul，
			不像@EnabelZuulProxy全部功能都包括了
	8. Zuul 过滤器
		* filterType有4种
			- pre
				> zuul路由之前（请求别的微服务之前）被执行，可以利用过滤器实现身份验证，
					在集群中选择请求的微服务，记录调试信息等
			- routing
				> 在路由过程中执行，这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient
					或者Netflix Ribbon请求微服务
			- post
				> 在路由到微服务后执行，这种过滤器可以用来为响应添加标准的Http Header、收集统计信息
					和指标、将响应从微服务发送给客户端等。
			- error
				> 其他过滤器执行错误就会执行error过滤器
			- 自定义过滤器类型
		* 过滤器执行顺序
			- pre -> routing -> post, 这3部分有出错就执行error
		* 实现过滤器
			- 创建类，实现ZuulFilter接口
				> public Object run() throws ZuulException {} 
					<> 具体业务逻辑
				> public boolean shouldFilter() {}
					<> 是否使用这个过滤器，true表示使用，false表示禁用
				> public int filterOrder() {}
					<> 过滤器执行顺序，返回的值越大，越后执行
				> public String filterType() {}
					<> 返回过滤器的类型
			- 启动类@Bean，配置新创建的过滤器
	9. 禁用Zuul的Filter
		* zuul.<SimpleClassName>.<filterType>.disable=true
	10. Zuul的回退
		* 事前准备
			- zuul增加/actuator/hystrix.stream的配置
			- 打开hystrix dashboard
		* 通过zuul访问用户微服务，用户微服务关闭，一直访问，直到断路器打开
			- 返回的是500的提示页面
			- 现在需要通过fallback定制返回页面
		* 创建配置类，实现FallbackProvider接口
			- 添加@Component注解
			- String getRoute(){}
				<> 是<route>.service-id,而不是yml配置里面的zuul.routes.<route>中的<route>
				<> 也可以直接return "*"; 通配所有微服务
			- ClientHttpResponse fallbackResponse(String route, Throwable cause) {}
				- 返回的Http响应，设置状态码，响应头，响应体的信息
		* 重新访问http://localhost:8040/api/user/user/id/2
			- 返回fallback
		* 回退
			- ribbon的回退使用@HystrixCommand。
			- feign的回退使用@Feignclient(fallback= xx.class)
			- Zuul的回退使用FallbackProvider
			- ribbon和feign都是针对方法回退，Zuul是针对微服务的回退
九、 使用Sidecar支持异构语言
	1. 将非java语言的微服务纳入到spring cloud的管理
	2. 下载安装node.js
	3. Sidecar要求异构语言的服务要实现健康指示器
	4. 编写node.js的服务
		var http = require("http");
		var url = require("url");
		var path = require("path");
		
		// 创建server
		var server = http.createServer(function(req, res){
			// 获取请求路径
			// pathname就是端口后面的，例如http://localhost:8080/aaa/bbb 那么pathname就是/aaa/bbb
			var pathname = url.parse(req.url).pathname;
			res.writeHead(200, {'Content-Type' : 'application/json; charset=utf-8'});
			// 访问http://localhost:8060/，将返回{'index':'欢迎来到首页'}
			if(pathname === '/'){
				res.end(JSON.stringify({'index' : '欢迎来到首页'}));
			} 
			// 如果访问htt[://localhost:8060/，将返回{'status':'UP'}
			else if(pathname === '/health.json') {
				res.end(JSON.stringify({'status' : 'UP'}));
			} 
			// 其他情况返回404
			else{
				res.end('404');
			}
		});
		// 创建监听并打印日志
		server.listen(8060, function(){
			console.log('listening on localhost:8060');
		});
	5. 打开cmd，在脚本所在的目录下执行:
		node node-service.js
		就运行node-service.js脚本
	6. 地址栏访问http://localhost:8060/
	7.  创建sidecar项目（sidecar、eurekadiscovery）
		* sidecar不在starter里面工具没有，需要自己加pom
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-netflix-sidecar</artifactId>
			</dependency>
	8. 启动类添加@EnableSidecar注解
		* @EnableSidecar整合了@EnableCircuitBreaker、@EnableZuulProxy
			其中@EnableZuulProxy又包括了@EnabelDiscoveryClient
	9. yml
		sidecar:
		  port: 8060 # 指定异构为服务的端口
		  health-uri: http://localhost:8060/health.json # 指定health指标
	10. 通过Zuul访问异构微服务
		* 地址栏输入http://localhost:8040/microservice-sidecar/
			或者http://localhost:8040/microservice-sidecar/aaa
			或者http://localhost:8040/microservice-sidecar/health.json
		* 通过Zuul再到Sidecar再到异构微服务
	11. 微服务调用异构微服务
		* restTemplate的url直接指向sidecar的微服务
			String vip = "http://microservice-sidecar/";
			return restTemplate.getForObject(vip, String.class);
		* 地址栏输入
			http://localhost:8040/microservice-ribbon-consumer/sidecar
	12. health的状态
		* health是sidecar微服务的health，localApplication的状态才是
			异构为服务微服务本身的状态
	13. 局限
		* sidecar和异构平台的微服务要运行在同一个host, 如果不想配置在同一个host上，
			需要配置eureka.instance.hostname
		* 一个异构微服务需要一个sidecar，1个微服务，3个实例分别是php,nodejs,...其他语言写，
			1个微服务3个实例就需要3个sidecar，100个微服务就需要300个sidecar
		* 别的语言也有自己的方式注册到eureka
十、Spring Cloud Config
	1. spring  cloud config是统一配置管理的组件
	2. 为什么要统一配置管理
		* 集中管理
			- 如果数据源改变了，每个微服务都需要修改配置，这样很不方便
		* 不同环境不同配置
			- 开发、测试、UAT、生产环境的数据源，连接池，并发数都不一样
		* 运行期间动态调整配置
			- 双11，淘宝需要不停机重启调大并发数
		* 自动刷新
			- 修改配置不用重启项目
	3. zookeeper和consul也能实现配置管理，淘宝，百度，携程在github都有配置管理项目
	4. spring cloud config为分布式系统外部化配置提供了服务器端和客户端的支持，它包括
		Config Server 和 Config Client。
		* Config Server 和Config Client都实现了对Spring Environment和PropertySource
			的抽象映射。
		* Spring Cloud Server默认使用git存储配置内容
			（也可以用svn，本地文件）
		* Config Client是Config Server的客户端，用于操作存储在Config Server的配置
	5. 编写Config Server
		* 创建项目（只添加Config Server）
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-config-server</artifactId>
			</dependency>
		* 启动类添加@EnableConfigServer注解
		* 配置yml
			- 配置端口
			- 配置git仓库路径
		* github创建仓库
			- https://github.com/liaoqichao/spring-cloud-config-repo
				> 创建项目时选择LICENSE, Apache LICENSE 2.0, 保证项目有东西，不然不让克隆
			- 克隆项目到本地
				> 右键GIT GUI选择远程仓库和本地存放地址
		* 在克隆下来的项目新建application.yml
			- yml添加
				profile: default-profile
		* 启动Config Server
		* 地址栏输入http://localhost:8080/abc-default.properties，页面显示git上application.yml
			的内容“profile: profile-default”
			- 输入http://localhost:8080/{application}-{profile}.properties
				其中{application}为任意应用，包括不存在的abc应用。{application}指的是
				微服务配的spring.application.name
			- {profile}指的是环境，例如eureka集群时配的profile: peer1
			- {label}指的是git的label，分支主线，默认是master
				也可以在地址栏输入
					http://localhost:8080/master/abc-default.properties
			- properties改成yml也一样
				http://localhost:8080/master/abc-default.yml
		* 提交foobar-dev.yml到github
			- 内容为profile: profile-dev
				> 地址栏输入http://localhost:8080/abc-default.properties报404
				> 地址栏输入http://localhost:8080/master/abc-default.properties可以正常访问
				> 地址栏输入http://localhost:8080/master/foobar-dev.properties
					<> 返回profile: profile-dev
				> 地址栏输入http://localhost:8080/master/foobar-default.properties
					<> 没有dev的profile
					<> 返回profile: profile-default
		* 找yml的优先级
			- 先找/{label}/{application}-{profile}.yml
			- 如果没找到，找application.yml
		* 所有映射规则在启动Config Server时控制台有打印出来
		* 地址栏输入http://localhost:8080/foobar/dev/master
			- /{application}/{profile}/{label}
			- 显示
				{
					"name":"foobar",
					"profiles":["dev"],
					"label":"master",
					"version":"b16947006f7f9a18b8318ff4bb471bef449289d9",
					"state":null,
					"propertySources":
						[
							{
								"name":"https://github.com/liaoqichao/config-repo/foobar-dev.yml",
								"source":{"profile":"profile-dev"}
							},
							{
								"name":"https://github.com/liaoqichao/config-repo/application.yml",
								"source":{"profile":"profile-default"}
							}
						]
				}
			- propertySources会列出来本身的yml和application.yml的信息， 但输入
				http://localhost:8080/master/foobar-dev.properties，显示的是第一个
			- propertySources里面2个name的url都是报404， 返回的是notfound，
				这个url只是标识符，表示yml在哪个仓库下的相对路径，表示
				在https://github.com/liaoqichao/config-repo的根目录“/”的foobar-dev.yml
	6. 编写Config Client
		* 选择Config Client、Web和devtools
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-config</artifactId>
			</dependency>
		* 配置application.yml
			server:
			  port: 8081
			spring:
			  cloud:
			    config:
			      uri: http://localhost:8080/
			      profile: dev  # 
			      label: master # github主线，当config server存储是git时候默认就是master
			  application:
			    name: foobar  # github上boobar-dev.yml上的{application}
		* 编写controller
		* 启动config server
		* 启动项目报错
			- controller的@Value(${profile})无法读取到值
			- Fetching config from server at: http://localhost:8888（默认值）
				> 配置了uri为 http://localhost:8080/，但日志显示的还是8888
		* spring boot 的 bootstrap application context
			- spring boot的启动上下文配置是用来加载远端的配置
			- 配置文件加载顺序：
				> 1. 加载bootstrap application context
					<> bootstrap.*
					<> 连接config server， 加载远程配置
				> 2. 加载本地的application.yml
					<> 加载application.*里面的配置
		* 创建bootstrap.yml
			spring:
			  cloud:
			    config:
			      uri: http://localhost:8080/
			      profile: dev  # 
			      label: master # github主线，当config server存储是git时候默认就是master
			  application:
			    name: foobar  # github上boobar-dev.yml上的{application}
		* application.yml
			server:
			  port: 8081
		* 启动config client项目
		* 地址栏输入http://localhost:8081/profile
			- 显示profile-dev
		* 当远程的foobar-dev.yml配置了profile, 本地application.yml也配置了profile， 以远程的为准
		* 如果client没有配置spring.application.name
			- 那么地址栏输入http://localhost:8081/profile，会获取git中application.yml的
				profile的值，profile-default
	7. Git仓库配置
		* git创建simple、special两个仓库，并克隆到本地
		* 把config-repo的application的yml复制到2个本地新仓库里面
			- 2个yml分别改profile的值改为simple和special
			- 提交到github
		* 一个config server配置多个git repository（多个微服务）
		* server.git.url使用通配符{application}（推荐）
			- config server的yml配置改为
				spring.cloud.config.server.git.url: 
					https://github.com/liaoqichao/{application}  # 改成通配符
			- 地址栏输入http://localhost:8080/simple-default.yml
				显示profile: simple
			- 地址栏输入http://localhost:8080/master/special-default.yml
				显示profile: special
			- 通过{application}实现了一个仓库对应一个微服务
			- 如果把全部yml都放在一个仓库，那么隔离性很差。
				> 一个项目，一个仓库，一个远程yml，一个远程默认yml
			- 也可以一种环境一个仓库
				spring.cloud.config.server.git.url: 
					https://github.com/liaoqichao/{application}-{profile}
		* 模式匹配（不推荐）
			- 配置
			spring:
			  cloud:
			    config:
			      server:
			        git:
			          uri: https://github.com/liaoqichao/config-repo  # 公用的application.yml
			          repos:
			            simple: 
			              pattern: simple*/dev*,simple*/test*		# 根据通配规则找
			              uri: https://github.com/liaoqichao/simple # 找不到则找公用的uri
			            special: 
			              pattern: special*/dev*,special*/test*
			              uri: https://github.com/liaoqichao/special # 找不到则找公用的uri
			- 地址栏输入http://localhost:8080/master/simple-default.yml（找不到，找公用）
				返回profile: profile-default
			- 地址栏输入http://localhost:8080/master/special-test.yml
				返回profile: special-test
		* search-path（推荐）
			- 一共只使用一个仓库，不同模块建不同目录存放，方便管理
			- git仓库创建foo文件夹，里面放foo.yml，提交到github
			- 配置
				spring:
				  cloud:
				    config:
				      server:
				        git:
				          uri: https://github.com/liaoqichao/config-repo  # 公用的application.yml
				          search-paths:
				          - foo # 路径，git仓库文件夹路径
				          - bar # 路径
			- search-paths也可以使用通配符
				search-paths: '{application}'
			- 地址栏输入http://localhost:8080/master/foo-dev.yml
				> 返回profile: foo-dev
				> 如果没找到则找公共的application.yml
		* 启动的时候下载github上的仓库
			- 全局和局部
			- 默认为false，不下仓库，在第一次请求的时候下载仓库
			- 局部的优先级高于全局
			spring:
			  cloud:
			    config:
			      server:
			        git:
			          uri: https://github.com/liaoqichao/config-repo  # 公用的application.yml
			          repos:
			            simple: 
			              pattern: simple*/dev*,simple*/test*
			              uri: https://github.com/liaoqichao/simple # 找不到则找公用的uri
			            special: 
			              pattern: special*/dev*,special*/test*
			              uri: https://github.com/liaoqichao/special # 找不到则找公用的uri
			              cloneOnStart: false # 局部，指定启动时克隆仓库
			          clone-on-start: true  # 全局，启动项目时克隆仓库， 默认为false
		* 私有仓库配置用户名和密码
			spring:
			  cloud:
			    config:
			      server:
			        git:
			          uri: https://github.com/liaoqichao/config-repo  # 公用的application.yml
			          username: 123
			          password: 123
	8. 对配置属性对称加解密
		* git上面的配置目前都是明文，但是如果数据源等敏感信息需要加密
		* 如果是自己搭建的git服务器，可以不用加密，如果是放在码云或者github则很不安全
		* 对配置属性加解密需要JCE(Java Cryptography Extension, Java 密码学扩展)
			- http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
			- 安装JCE
				> 解压
				> 2个jar包一个read me.txt
					<> jar包是加解密的策略文件
				> 根据read me.txt的Installation安装
					<> 具体就是替换jdk自带的加解密文件(覆盖jar包)
					<> 目录在jdk/jre/lib/security/目录
				> 下载的JCE与jdk的JCE对比
					<> 下载的JCE去掉了加密强度的限制
		* config server的yml添加下面配置
			encrypt:
			  key: foo	# 加密因子
		* 启动config server
			- 通过curl工具输入
				curl -X POST http://localhost:8080/encrypt -d foobar
				返回对对foobar加密的内容XXXX
				> 只能通过POST请求访问
			- 编写html
		* 远程仓库新建一个foobar-production.yml, 一个foobar-test.properties
			- foobar-production.yml里面写
				profile: '{cipher}XXXXX'
				> 其中XXXXX是加密后的"foobar-production"
			- foobar-test.properties里面写
				profile={cipher}XXXXX
				> 注意使用properties，value没有引号
		* 启动config server，地址栏输入http://localhost:8080/master/foobar-production.properties
			返回的是解密后的内容：profile: foobar
		* config客户端不需要改动
	9. 对配置属性非对称加解密
	10. Config Server安全认证
		* 添加spring-boot-starter-security依赖
			- 一共就config server和spring security
		* yml配置账号密码
			- 和eureka server配置的一样
	11. Config Client连需要认证的Config Server
		* 和eureka client一样，uri加上用户名和密码
		* uri同级的配置还有username和password
		* 如果uri和配置属性都配了用户名和密码，属性的优先级高，按照属性配的来
	12. Spring Cloud Config配合Eureka使用
		* Config Client的yml里面硬编码Config Server的ip、端口，而服务的发现与注册刚好就是为了解决这个问题，
			所以Spring Cloud Config也要服务注册与发现。
			- 目标：让Config Client具备服务发现
		* 把Config Server注册到eureka
		* 把Config Client注册到eureka
			- 修改yml配置，添加下面配置，去掉git.url，但保留label，profile等配置
				spring:
				  cloud:
				    config:
				      discovery:
				        enabled: true # 允许通过服务发现找config server
				        service-id: config-server # config server的service-id
		* 之前配置uri的方式叫做配置优先（Config First Bootstrap）， 这种注册到eureka的方式叫做发现优先（Discovery First Bootstrap）
	13. 配置刷新
		* 配置刷新是指配置属性的自动刷新
			- git上的foobar-dev.yml的profile属性的值为profile-dev，当prifile的值改为
				profileabc时，希望config client的profile的值也跟着变化而不需要停机。
				> 例如git仓库的yml配置了数据库的连接数，双11需要修改它的配置而不重启系统
		* 实际生产上用的比较上，还是会重启，因为不知道修改的配置是否生效。还需要大量的测试，测试修改的配置生效，不如
			直接重启完事。
			- 一个项目有上百项配置，一次修改了十几项配置，还需要一个一个的测试修改配置是否生效，很麻烦。
		* 手动刷新
			- config server不需要修改
			- config client（微服务上修改）
				> pom.xml添加actuator依赖，暴露refresh端点
					<> 需要暴露refresh端点
				> 要用到的类配置的类上面添加@RefreshScope注解
					<> 使用这个注解，不用担心事情做到一半，突然配置改变了导致报错。正在使用的配置会让他继续走完，
						后面新的请求过来就用新的配置
				> POST请求http://localhost:8081/refresh，通过REST端点刷新
					<> 连接config server
					<> 重新从server获取配置信息
					<> 返回刷新的属性
						["config.client.version","profile"]
			- 缺点
				> 有m个微服务，每个微服务有n个结点，需要访问m*n个url
			- 注意
				> 强烈建议@Configuration和@RefreshScope不要注解在同一个类上			
		* 自动刷新（推送消息和Spring Cloud Bus）
			- 博客
				> https://blog.csdn.net/chou342175867/article/details/79697035
			- Spring Cloud Bus基于AMQP协议，需要导入spring-cloud-starter-bus-amqp
				或者spring-cloud-starter-bus-kafka依赖（依然rabbitMQ或者kafka），这里选择rabbitMQ
			- pivotal公司的项目
				> rabbitMQ
				> Spring
				> redis
				> greenplum
				> reactor
				> PFC pivotal cloud foundry
			- 下载rabbitmq
				> 下载Erlang for Windows 
					<> http://www.erlang.org/downloads
					<> rabbitmq是用Erlang（一种语言）开发的
				> 安装erlang
					<> 运行安装包，选择安装内容，全都选上
				> 下载rabbitmq server
					<> http://www.rabbitmq.com/install-windows.html
				> 运行rabbitmq server安装包
					<> 安装完后，windows服务多了一个rabbitmq服务
				> 下载rabbitmq图形界面工具
					<> 帮助页面www.rabbitmq.com/management.html
					<> 按开始，找到rabbitmq的cmd
					<> 输入下面命令
						.\rabbitmq-plugins enable rabbitmq_management
					<> 重启rabbitmq
					<> 打开浏览器，访问http://localhost:15672
						[] 账号密码都是guest
			- config client添加spring-cloud-starter-bus-amqp依赖
				> web、eureka discovery、actuator、config client、 rabbitmq
			- 配置yml，连上rabbitmq
				spring:
				  rabbitmq:
				    host: localhost
				    port: 5672
				    username: guest
				    password: guest
			- 启动config server
			- 启动2个config client
			- 修改git端的配置
			- POST请求，请求头ContentType=application/json，请求任意一个客户端
				的http://localhost:8081/bus/refresh，再访问
				http://localhost:8081/master/foobar-dev.properties和
				http://localhost:8082/master/foobar-dev.properties，
				配置都改变了
				> 输入http://localhost:8081/bus/refresh，触发事件，修改了所有节点的值
				> curl -X POST http://localhost:8081/bus/refresh
			- github添加webhook
				> 执行XX事件，就访问url，访问的密码为psw
				> 事件选择push，url选择http://localhost:8081/bus/refresh，
					填写密码，contentType选择application/json
			- 问题
				> 是访问一个微服务的/bus/refresh，通过spring cloud bus通知到其他微服务，
					这样相同都是微服务A，但是角色就不同，一个要主动刷新，其他则接收消息刷新。
				> 重新部署的时候ip和端口会发生变化
				> 解决方法：
					<> Config Server也加上spring-cloud-starter-bus-amqp依赖
					<> Config Server的yml也配上spring.rabbitmq.host/port/username/password
					<> webhook只需要请求config server的/bus/refresh
		* 局部刷新
			- 有时候不希望全部实例都刷新配置，需要用到局部刷新
			- /bus/refresh?destination=ApplicationContext的Id:端口号
				> 默认就是spring.application.name:server.port
				> 如果2个实例分别是182.168.2.1:8080 和 182.168.2.2:8080，
					它们的“ApplicationContext的Id:端口号”是一样的，如果使用
					“/bus/refresh?destination=...”只会更新一个实例
				> 真正生产上client还需要配置
					spring.application.index: ${random.long} # 给一个随机的index
			- /bus/refresh?destination=ApplicationContext的Id:**
				> 所有相同的微服务的conf clien配置都会刷新
	14. Config Server高可用
		* git仓库的高可用
		* RabbitMQ高可用
			- 自己搭建RabbitMQ集群
				> http://www.rabitmq.com/ha.html
		* Config Server本身的高可用
			- 未注册到eureka上面(Config Bootstrap First)
				> Client -> 负载均衡器 -> Server cluster
			- 注册到eureka上（Discovery Bootstrap First）
				> client（带ribbon） -> eureka, client-> server cluster
			- 使用云服务