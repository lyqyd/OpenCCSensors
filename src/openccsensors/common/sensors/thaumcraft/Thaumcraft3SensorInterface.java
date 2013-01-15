package openccsensors.common.sensors.thaumcraft;

import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import openccsensors.common.api.ISensorAccess;
import openccsensors.common.api.ISensorInterface;
import openccsensors.common.api.ISensorTarget;
import openccsensors.common.helper.TargetHelper;
import openccsensors.common.retrievers.ITileEntityValidatorCallback;
import openccsensors.common.retrievers.TileEntityRetriever;
import openccsensors.common.sensors.SensorCard;

public class Thaumcraft3SensorInterface  implements ISensorInterface {

	private TileEntityRetriever retriever = new TileEntityRetriever();
	
	public static final String BRAIN_IN_A_JAR_CLASS = "thaumcraft.common.blocks.jars.TileJarBrain";
	public static final String CRUCIBLE_CLASS = "thaumcraft.common.blocks.TileCrucible";
	public static final String FILLED_JAR_CLASS = "thaumcraft.common.blocks.jars.TileJarFillable";
	
	public Thaumcraft3SensorInterface()
	{

		retriever.registerCallback(new ITileEntityValidatorCallback() {
			@Override
			public ISensorTarget getTargetIfValid(TileEntity entity, int relativeX, int relativeY, int relativeZ) {
				
				String className = entity.getClass().getName();
				
				if (className.equals(BRAIN_IN_A_JAR_CLASS))
				{
					return new BrainInAJarTarget(entity, relativeX, relativeY, relativeZ);
				}
				else if (className.equals(CRUCIBLE_CLASS))
				{
					return new CrucibleTarget(entity, relativeX, relativeY, relativeZ);
				}
				else if (className.equals(FILLED_JAR_CLASS))
				{
					return new FilledJarTarget(entity, relativeX, relativeY, relativeZ);
				}
				return null;
			}
		});
	}
	
	@Override
	public Map callMethod(ISensorAccess sensor, World world, int x, int y,
			int z, int methodID, Object[] args, int cardMark) throws Exception {
		return null;
	}

	@Override
	public Map getBasicTarget(ISensorAccess sensor, World world, int x, int y,
			int z, int cardMark) throws Exception {
		
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
		return 24;
	}

	@Override
	public String[] getMethods() {
		return null;
	}

	@Override
	public String getName() {
		return "openccsensors.item.thaumcraft3sensor";
	}

	@Override
	public Map getTargetDetails(ISensorAccess sensor, World world, int x,
			int y, int z, int cardMark, String target) throws Exception {

		return TargetHelper.getDetailedInformationForTarget(target,
				retriever.getCube(world, x, y, z), world);
		
	}

	@Override
	public void initRecipes(SensorCard card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirectionalEnabled() {
		return false;
	}
}
