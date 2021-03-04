# zookeeper


#### @Path注解
@Path,属性对应的地址.
实现类可包括Zookeeper以及Redis.


#### IComponent接口
最顶层接口.自己需要实现组件

#### ZkComponent
实现了IComponent接口.
将属性值存放到Zookeeper中,当Zookeeper中的值发生变化时,类的属性也会自动更新。
构造方法：
```
无参构造：默认ZK服务器地址是localhost:2181
public ZkComponent();

有参构造：可指定ZK服务器地址
public ZkComponent(String zkHosts);
```


#### 示例
LocationAnalyzer示例:
其中locationHost以及locationPort会自动随着ZK中的对应地址值发生变化。
```
public class LocationAnalyzer extends ZkComponent {
	@Path(value = "/locationHost")
	private String locationHost;
	
	@Path(value = "/locationPort")
	private String locationPort;

	public LocationAnalyzer() throws IOException {
		super();
	}
	
	public LocationAnalyzer(String zkHosts) throws IOException {
		super(zkHosts);
	}
}
```