package com.anytec.sdproperty.jedis;

import com.anytec.sdproperty.model.TbDoorLock;
import com.anytec.sdproperty.model.TbGuest;
import com.anytec.sdproperty.model.TbGuestRole;
import com.anytec.sdproperty.model.TbIpc;

public interface NecessaryDataService {

    TbIpc getCamera(String mac);

    TbGuest getTbGuest(String person_id);

    TbGuestRole getTbGuestRole(Integer guestRoleId);

    TbDoorLock getTbOpenLock(Integer doorId);

    TbDoorLock getTbDangerLock(Integer doorId);


}
