package au.id.villar.ubuntuOne.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountInfo {

	private String username;
	private String openId;
	private String firstName;
	private String lastName;
	private String nickname;
	private String email;
	private int id;
	private String couchDBRoot;
	private CouchDB couchBD;
	private List<String> features;
	private String currentPlan;
	private Subscription subscription;
	private long totalStorage;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty("openid")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("couchdb_root")
	public String getCouchDBRoot() {
		return couchDBRoot;
	}

	public void setCouchDBRoot(String couchDBRoot) {
		this.couchDBRoot = couchDBRoot;
	}

	@JsonProperty("couchdb")
	public CouchDB getCouchBD() {
		return couchBD;
	}

	public void setCouchBD(CouchDB couchBD) {
		this.couchBD = couchBD;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	@JsonProperty("current_plan")
	public String getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(String currentPlan) {
		this.currentPlan = currentPlan;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@JsonProperty("total_storage")
	public long getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(long totalStorage) {
		this.totalStorage = totalStorage;
	}

	@Override
	public String toString() {
		return "AccountInfo{" +
				"username='" + username + '\'' +
				", openId='" + openId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", nickname='" + nickname + '\'' +
				", email='" + email + '\'' +
				", id=" + id +
				", couchDBRoot='" + couchDBRoot + '\'' +
				", couchBD=" + couchBD +
				", features=" + features +
				", currentPlan='" + currentPlan + '\'' +
				", subscription=" + subscription +
				", totalStorage=" + totalStorage +
				'}';
	}

	//@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CouchDB {

		private String host;
		private String root;
		private String dbPath;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getRoot() {
			return root;
		}

		public void setRoot(String root) {
			this.root = root;
		}

		@JsonProperty("dbpath")
		public String getDbPath() {
			return dbPath;
		}

		public void setDbPath(String dbPath) {
			this.dbPath = dbPath;
		}

		@Override
		public String toString() {
			return "CouchDB{" +
					"host='" + host + '\'' +
					", root='" + root + '\'' +
					", dbPath='" + dbPath + '\'' +
					'}';
		}
	}

	//@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Subscription {
		private String description;
		private String currency;
		private String started;
		private boolean paid;
		private String expires;
		private int id;
		private float price;
		private boolean trial;
		private int quantity;
		private boolean upgradeAvailable;
		private String name;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getStarted() {
			return started;
		}

		public void setStarted(String started) {
			this.started = started;
		}

		@JsonProperty("is_paid")
		public boolean isPaid() {
			return paid;
		}

		public void setPaid(boolean paid) {
			this.paid = paid;
		}

		public String getExpires() {
			return expires;
		}

		public void setExpires(String expires) {
			this.expires = expires;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public boolean isTrial() {
			return trial;
		}

		public void setTrial(boolean trial) {
			this.trial = trial;
		}

		@JsonProperty("qty")
		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		@JsonProperty("upgrade_available")
		public boolean isUpgradeAvailable() {
			return upgradeAvailable;
		}

		public void setUpgradeAvailable(boolean upgradeAvailable) {
			this.upgradeAvailable = upgradeAvailable;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Subscription{" +
					"description='" + description + '\'' +
					", currency='" + currency + '\'' +
					", started='" + started + '\'' +
					", paid=" + paid +
					", expires='" + expires + '\'' +
					", id=" + id +
					", price=" + price +
					", trial=" + trial +
					", quantity=" + quantity +
					", upgradeAvailable=" + upgradeAvailable +
					", name='" + name + '\'' +
					'}';
		}
	}

}
