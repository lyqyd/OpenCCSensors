package openccsensors.common.sensors.vanilla;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;
import openccsensors.common.api.ISensorAccess;
import openccsensors.common.api.ISensorInterface;
import openccsensors.common.api.ISensorTarget;
import openccsensors.common.helper.TargetHelper;
import openccsensors.common.retrievers.ITileEntityValidatorCallback;
import openccsensors.common.retrievers.TileEntityRetriever;
import openccsensors.common.sensors.SensorCard;
import cpw.mods.fml.common.registry.GameRegistry;

public class SignSensorInterface implements ISensorInterface {

	private TileEntityRetriever retriever = new TileEntityRetriever();

	public SignSensorInterface() {
		retriever.registerCallback(new ITileEntityValidatorCallback() {
			@Override
			public ISensorTarget getTargetIfValid(TileEntity entity, int relativeX, int relativeY, int relativeZ) {
				if (entity instanceof TileEntitySign)
				{
					return new SignPostTarget(entity, relativeX, relativeY, relativeZ);
				}
				return null;
			}
		});

	}

	@Override
	public Map callMethod(ISensorAccess sensor, World world, int x, int y, int z, int methodID, Object[] args, int cardMark) throws Exception {
		return null;
	}

	@Override
	public Map getBasicTarget(ISensorAccess sensor, World world, int x, int y, int z, int cardMark)
			throws Exception {

		return TargetHelper.getBasicInformationForTargets(
				retriever.getCube(world, x, y, z), world);

	}
	
	@Override
	public ISensorTarget getRelevantTargetForGauge(World world, int x, int y,
			int z) {
		return null;
	}

	@Override
	public int getId() {
		return 23;
	}

	@Override
	public String[] getMethods() {
		return null;
	}

	@Override
	public String getName() {
		return "openccsensors.item.signsensor";
	}

	@Override
	public Map getTargetDetails(ISensorAccess sensor, World world, int x, int y, int z, int cardMark, String target)
			throws Exception {

		return TargetHelper.getDetailedInformationForTarget(target,
				retriever.getCube(world, x, y, z), world);

	}

	@Override
	public void initRecipes(SensorCard card) {
		GameRegistry.addRecipe(
				new ItemStack(card, 1, this.getId()),
				"rsr",
				"rrr",
				"aaa",
				'r', new ItemStack(Item.redstone),
				'a', new ItemStack(Item.paper),
				's',new ItemStack(Item.sign));
	}

	@Override
	public boolean isDirectionalEnabled() {
		return false;
	}

}